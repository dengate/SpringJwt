package com.amazon.stock.mapping;

import com.amazon.stock.model.Product;
import com.amazon.stock.resource.ProductResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends IEntityMapper<ProductResource, Product>{
}
