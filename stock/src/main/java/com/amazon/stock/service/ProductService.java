package com.amazon.stock.service;

import com.amazon.stock.enums.ErrorCode;
import com.amazon.stock.exception.EmptyTableException;
import com.amazon.stock.exception.GeneralErrorException;
import com.amazon.stock.exception.ProductNotFoundException;
import com.amazon.stock.exception.SaveEntityErrorException;
import com.amazon.stock.mapping.ProductMapper;
import com.amazon.stock.model.Category;
import com.amazon.stock.model.Product;
import com.amazon.stock.repository.ProductRepository;
import com.amazon.stock.resource.CategoryResource;
import com.amazon.stock.resource.ProductResource;
import com.amazon.stock.response.ProductListResponse;
import com.amazon.stock.response.ProductResponse;
import com.amazon.stock.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;
    @Autowired
    ProductMapper mapper;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    Functions functions;

    public ProductResponse addProduct(ProductResource resource) throws GeneralErrorException,SaveEntityErrorException{
        ProductResponse response = new ProductResponse();
        try {
            int i = 0;
            for (CategoryResource category : resource.categories){
                category = categoryService.entityToResource(categoryService.findByCategoryId(category.categoryId));
                resource.categories.remove(i);
                resource.categories.add(i,category);
                i++;
            }
            String now = functions.now("yyyy-MM-dd");
            resource = createShareEntity(resource,true,now,now,1,1);
            Product product = repository.save(mapper.toEntity(resource));
            for (CategoryResource category : resource.categories){
                Category category1 = categoryService.findByCategoryId(category.categoryId);
                category1.getProducts().add(product);
                categoryService.save(category1);
            }
            if (product != null){
                response.product = mapper.toResource(product);
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

    public ProductResponse deleteProduct(int productId) throws GeneralErrorException, ProductNotFoundException,SaveEntityErrorException {
        ProductResponse response = new ProductResponse();
        try {
            ProductResource productResource = mapper.toResource(repository.findByProductIdAndStatusIsTrue(productId));
            if (productResource == null){
                throw new ProductNotFoundException();
            }
            else {
                String now = functions.now("yyyy-MM-dd");
                productResource = createShareEntity(productResource, false, now, productResource.createDate, productResource.modifyUserId, productResource.createUserId);
                Product product = repository.save(mapper.toEntity(productResource));
                if (product != null) {
                    response.product = mapper.toResource(product);
                    response.meta = functions.createSuccessMeta();
                }
                else {
                    throw new SaveEntityErrorException();
                }
            }
            return response;
        }
        catch (Exception e) {
            throw new GeneralErrorException();
        }
    }

    @Cacheable("products")
    public ProductListResponse getProducts(Integer pageNo,Integer pageSize,String sortBy) throws GeneralErrorException,EmptyTableException{
        ProductListResponse response = new ProductListResponse();
        try {
            Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            List<Product> products = repository.findAllByStatusIsTrue(page);
            if(products == null){
                throw new EmptyTableException();
            }
            else {
                response.products = mapper.toResource(products);
                response.meta = functions.createSuccessMeta();
            }
            return response;
        }
        catch (Exception e) {
            throw new GeneralErrorException();
        }
    }

    @Cacheable("productsByCategoryId")
    public ProductListResponse getProductsFindByCategoryId(Integer pageNo,Integer pageSize,String sortBy,int categoryId) throws GeneralErrorException,EmptyTableException{
        ProductListResponse response = new ProductListResponse();
        try {
            Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Category category = categoryService.findByCategoryId(categoryId);
            List<Product> products = repository.findAllByCategoriesContainsAndStatusIsTrue(category,page);
            if(products == null){
                throw new EmptyTableException();
            }
            else {
                response.products = mapper.toResource(products);
                response.meta = functions.createSuccessMeta();
            }
            return response;
        }
        catch (Exception e) {
            throw new GeneralErrorException();
        }
    }

    @Cacheable("productsByMerchantId")
    public ProductListResponse getProductsFindByMerchantId(Integer pageNo,Integer pageSize,String sortBy,int storeId) throws GeneralErrorException,EmptyTableException{
        ProductListResponse response = new ProductListResponse();
        try {
            Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            List<Product> products = repository.findAllByStoreIdAndStatusIsTrue(storeId,page);
            if(products == null){
                throw new EmptyTableException();
            }
            else {
                response.products = mapper.toResource(products);
                response.meta = functions.createSuccessMeta();
            }
            return response;
        }
        catch (Exception e) {
            throw new GeneralErrorException();
        }
    }

    public ProductResource createShareEntity(ProductResource resource, boolean status, String modifyDate, String crateDate, int modifyUserId, int createUserId){
        resource.status = status;
        resource.modifyDate = modifyDate;
        resource.createDate = crateDate;
        resource.modifyUserId = modifyUserId;
        resource.createUserId = createUserId;
        return resource;
    }
}
