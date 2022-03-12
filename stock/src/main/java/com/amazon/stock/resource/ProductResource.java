package com.amazon.stock.resource;


import java.util.List;

public class ProductResource extends ShareEntityResource{
    public int productId;
    public int storeId = 0;
    public String name;
    public double amount = 0;
    public String imgUrl;
    public String description;
    public int stock = 0;
    public List<CategoryResource> categories;
}
