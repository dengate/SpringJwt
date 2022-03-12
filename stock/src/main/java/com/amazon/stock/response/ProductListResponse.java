package com.amazon.stock.response;

import com.amazon.stock.resource.ProductResource;

import java.util.List;

public class ProductListResponse extends ResponseBase{
    public List<ProductResource> products;
}
