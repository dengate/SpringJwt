package com.amazon.stock.service;

import com.amazon.stock.enums.ErrorCode;
import com.amazon.stock.response.CategoryListResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test
    void getCategories() {
        CategoryListResponse categoryListResponse = categoryService.getCategories();
        assertNotNull(categoryListResponse);
        assertTrue(categoryListResponse.meta.errorCode == ErrorCode.HTTP_SUCCESS.code);
    }
}