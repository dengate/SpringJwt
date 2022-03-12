package com.amazon.stock.mapping;

import com.amazon.stock.model.Category;
import com.amazon.stock.resource.CategoryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends IEntityMapper<CategoryResource, Category>{
}
