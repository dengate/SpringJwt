package com.amazon.stock.controller;

import com.amazon.stock.exception.*;
import com.amazon.stock.helpers.Helper;
import com.amazon.stock.resource.ProductResource;
import com.amazon.stock.response.ProductListResponse;
import com.amazon.stock.response.ProductResponse;
import com.amazon.stock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductResource resource){
        ProductResponse response = new ProductResponse();
        if(resource == null || resource.name == null || resource.amount == 0 || resource.storeId == 0 || resource.stock == 0 || resource.categories == null)
            throw new ValidationErrorException();
        else {
            try {
                response.meta = Helper.HELPER.runtime.generateMeta(Helper.HELPER.constant.HTTP_SUCCES);
                response = service.addProduct(resource);
            }
            catch (SaveEntityErrorException | GeneralErrorException e) {
                throw e;
            }
            return response;
        }
    }

    @GetMapping("/deleteProduct")
    public ProductResponse deleteProduct(@RequestParam(defaultValue = "0") Integer productId){
        ProductResponse response = new ProductResponse();
        if(productId == 0)
            throw new ValidationErrorException();
        else {
            try {
                response.meta = Helper.HELPER.runtime.generateMeta(Helper.HELPER.constant.HTTP_SUCCES);
                response = service.deleteProduct(productId);
            }
            catch (SaveEntityErrorException | CategoryNotFoundException | GeneralErrorException e) {
                throw e;
            }
            return response;
        }
    }

    @GetMapping
    public ProductListResponse getProducts(@RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "productId") String sortBy){
        ProductListResponse response = new ProductListResponse();
        try {
            response.meta = Helper.HELPER.runtime.generateMeta(Helper.HELPER.constant.HTTP_SUCCES);
            response = service.getProducts(pageNo,pageSize,sortBy);
        }
        catch (EmptyTableException | GeneralErrorException e) {
            throw e;
        }
        return response;
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

            }
            catch (EmptyTableException | GeneralErrorException e) {
                throw e;
            }
        }
        return response;
    }

    @GetMapping("/getProductsFindByMerchantId")
    public ProductListResponse getProductsFindByMerchantId(@RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "productId") String sortBy,
                                           @RequestParam(defaultValue = "0") Integer storeId){
        ProductListResponse response = new ProductListResponse();
        if(storeId == 0)
            throw new ValidationErrorException();
        else {
            try {
                response.meta = Helper.HELPER.runtime.generateMeta(Helper.HELPER.constant.HTTP_SUCCES);
                response = service.getProductsFindByMerchantId(pageNo, pageSize, sortBy, storeId);
            }
            catch (EmptyTableException | GeneralErrorException e) {
                throw e;
            }
        }
        return response;
    }
}
