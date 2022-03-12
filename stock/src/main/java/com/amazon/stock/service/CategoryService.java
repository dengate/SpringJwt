package com.amazon.stock.service;

import com.amazon.stock.enums.ErrorCode;
import com.amazon.stock.exception.CategoryNotFoundException;
import com.amazon.stock.exception.EmptyTableException;
import com.amazon.stock.exception.GeneralErrorException;
import com.amazon.stock.exception.SaveEntityErrorException;
import com.amazon.stock.mapping.CategoryMapper;
import com.amazon.stock.mapping.ProductMapper;
import com.amazon.stock.model.Category;
import com.amazon.stock.repository.CategoryRepository;
import com.amazon.stock.resource.CategoryResource;
import com.amazon.stock.resource.ProductResource;
import com.amazon.stock.response.CategoryListResponse;
import com.amazon.stock.response.CategoryResponse;
import com.amazon.stock.response.ProductListResponse;
import com.amazon.stock.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository repository;
    @Autowired
    CategoryMapper mapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    Functions functions;
    /*@Autowired
    ProductService productService;*/


    public CategoryResponse addCategory(CategoryResource resource) throws GeneralErrorException,SaveEntityErrorException
    {
        CategoryResponse response = new CategoryResponse();
            try {
                String now = functions.now("yyyy-MM-dd");
                resource = createShareEntity(resource,true,now,now,1,1);
                Category category = repository.save(mapper.toEntity(resource));
                if (category != null){
                    response.category = mapper.toResource(category);
                    response.meta = functions.createSuccessMeta();
                }
                else {
                    throw new SaveEntityErrorException();
                }
                return response;
            }
            catch (Exception e) {
                throw new GeneralErrorException();
            }
    }

    public CategoryResponse deleteCategory(int categoryId) throws GeneralErrorException,SaveEntityErrorException
    {
        CategoryResponse response = new CategoryResponse();
        try {
            CategoryResource resource = mapper.toResource(repository.findByCategoryIdAndStatusIsTrue(categoryId));
            if(resource == null){
                response.meta.errorCode = ErrorCode.CATEGORY_NOT_FOUND.code;
            }
            else {
                String now = functions.now("yyyy-MM-dd");
                resource = createShareEntity(resource, false, now, resource.createDate, resource.modifyUserId, resource.createUserId);
                Category category = repository.save(mapper.toEntity(resource));
                if (category != null) {
                    response.category = mapper.toResource(category);
                    response.meta = functions.createSuccessMeta();
                } else {
                    throw new SaveEntityErrorException();
                }
            }
            return response;
        }
        catch (Exception e) {
            throw new GeneralErrorException();
        }
    }

    @Cacheable("categories")
    public CategoryListResponse getCategories() throws GeneralErrorException,EmptyTableException
    {
        CategoryListResponse response = new CategoryListResponse();
        try {
            List<Category> categories = repository.findAllByStatusIsTrue();
            if(categories == null){
                throw new EmptyTableException();
            }
            else {
                response.categories = mapper.toResource(categories);
                response.meta = functions.createSuccessMeta();
            }
            return response;
        }
        catch (Exception e) {
            throw new GeneralErrorException();
        }
    }

    @Cacheable("productsByCategoryId2")
    public ProductListResponse getProductsFindByCategoryId(Integer pageNo, Integer pageSize, String sortBy, int categoryId) throws GeneralErrorException{
        ProductListResponse response = new ProductListResponse();
        try {
            Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            List<ProductResource> products = productMapper.toResource(repository.findAllByStatusIsTrueAndCategoryId(page,categoryId));
            if(products == null){
                response.meta.errorCode = ErrorCode.EMPTY_TABLE.code;
            }
            else {
                response.products = products;
                response.meta = functions.createSuccessMeta();
            }
            return response;
        }
        catch (Exception e) {
            throw new GeneralErrorException();
        }
    }


    public Category findByCategoryId(int categoryId) throws CategoryNotFoundException{
        Category category = repository.findByCategoryIdAndStatusIsTrue(categoryId);
        if (category == null){
            throw new CategoryNotFoundException();
        }
        else{
            return category;
        }
    }

    public CategoryResource createShareEntity(CategoryResource resource, boolean status, String modifyDate, String crateDate, int modifyUserId, int createUserId){
        resource.status = status;
        resource.modifyDate = modifyDate;
        resource.createDate = crateDate;
        resource.modifyUserId = modifyUserId;
        resource.createUserId = createUserId;
        return resource;
    }

    public CategoryResource entityToResource(Category category){
        return mapper.toResource(category);
    }

    public Category resourceToEntity(CategoryResource resource){
        return mapper.toEntity(resource);
    }

    public List<Category> resourceListToEntityList(List<CategoryResource> resources){
        return mapper.toEntity(resources);
    }

    public List<CategoryResource> entityListToResourceList(List<Category> categories){
        return mapper.toResource(categories);
    }

    public void save(Category category){
        repository.save(category);
    }

}
