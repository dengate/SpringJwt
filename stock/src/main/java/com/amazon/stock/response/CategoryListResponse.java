package com.amazon.stock.response;

import com.amazon.stock.resource.CategoryResource;

import java.util.List;

public class CategoryListResponse extends ResponseBase{
    public List<CategoryResource> categories;
}
