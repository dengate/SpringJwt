package com.amazon.stock.service.interfaces;

import com.amazon.stock.model.Category;
import com.amazon.stock.resource.CategoryResource;
import com.amazon.stock.response.CategoryListResponse;
import com.amazon.stock.response.CategoryResponse;
import com.amazon.stock.response.ProductListResponse;

import java.util.List;

public interface ICategoryService {
    CategoryResponse addCategory(CategoryResource resource);
    CategoryResponse deleteCategory(int categoryId);
    CategoryListResponse getCategories();
    ProductListResponse getProductsFindByCategoryId(Integer pageNo, Integer pageSize, String sortBy, int categoryId);
    Category findByCategoryId(int categoryId);
    CategoryResource entityToResource(Category category);
    Category resourceToEntity(CategoryResource resource);
    List<Category> resourceListToEntityList(List<CategoryResource> resources);
    List<CategoryResource> entityListToResourceList(List<Category> categories);
    void save(Category category);
}
