package com.amazon.stock.controller;

import com.amazon.stock.enums.ErrorCode;
import com.amazon.stock.exception.*;
import com.amazon.stock.helpers.Helper;
import com.amazon.stock.resource.CategoryResource;
import com.amazon.stock.response.CategoryListResponse;
import com.amazon.stock.response.CategoryResponse;
import com.amazon.stock.response.ProductListResponse;
import com.amazon.stock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock/category")
@CrossOrigin(origins = "http://localhost:5052",allowedHeaders = "*")
public class CategoryController {

    @Autowired
    CategoryService service;



    @PostMapping
    public CategoryResponse addCategory(@RequestBody CategoryResource resource){
        CategoryResponse response = new CategoryResponse();
        if(resource == null || resource.name == null)
            throw new ValidationErrorException();
        else {
            try {
                response.meta = Helper.HELPER.runtime.generateMeta(Helper.HELPER.constant.HTTP_SUCCES);
                response = service.addCategory(resource);
            }
            catch (SaveEntityErrorException | GeneralErrorException e) {
                throw e;
            }
            return response;
        }
    }

    @GetMapping("/deleteCategory")
    public CategoryResponse deleteCategory(@RequestParam(defaultValue = "0") Integer categoryId){
        CategoryResponse response = new CategoryResponse();
        if(categoryId == 0)
            throw new ValidationErrorException();
        else {
            try {
                response.meta = Helper.HELPER.runtime.generateMeta(Helper.HELPER.constant.HTTP_SUCCES);
                response = service.deleteCategory(categoryId);
                if(response.meta.errorCode == ErrorCode.CATEGORY_NOT_FOUND.code){
                    throw new CategoryNotFoundException();
                }
            }
            catch (SaveEntityErrorException | GeneralErrorException e) {
                throw e;
            }
            return response;
        }
    }

    @GetMapping("/getProductsFindByCategoryId")
    public ProductListResponse getProductsFindByCategoryId(@RequestParam(defaultValue = "0") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "productId") String sortBy,
                                                           @RequestParam(defaultValue = "0") Integer categoryId){
        ProductListResponse response = new ProductListResponse();
        if(categoryId == 0)
            throw new ValidationErrorException();
        else {
            try {
                response.meta = Helper.HELPER.runtime.generateMeta(Helper.HELPER.constant.HTTP_SUCCES);
                response = service.getProductsFindByCategoryId(pageNo, pageSize, sortBy, categoryId);
                if (response.meta.errorCode == ErrorCode.EMPTY_TABLE.code) {
                    throw new EmptyTableException();
                }
            } catch (GeneralErrorException e) {
                throw e;
            }
        }
        return response;
    }

    @GetMapping
    public CategoryListResponse getCategories(){
        CategoryListResponse response = new CategoryListResponse();
            try {
                response.meta = Helper.HELPER.runtime.generateMeta(Helper.HELPER.constant.HTTP_SUCCES);
                response = service.getCategories();
            }
            catch (EmptyTableException | GeneralErrorException e) {
                throw e;
            }
            return response;
    }

}
