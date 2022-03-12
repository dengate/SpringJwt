package com.amazon.stock.enums;

public enum ErrorMessage {
    SUCCESS("Success"),
    ERROR("Error"),
    VALIDATION_ERROR("Validation Error!"),
    SAVE_ENTITY_ERROR("DataBase Error"),
    CATEGORY_NOT_FOUND("Category Not Found"),
    EMPTY_TABLE("Table is Empty"),
    PRODUCT_NOT_FOUND("Product Not Found"),
    FORBİDDEN("Forbidden");

    public final String message;

    ErrorMessage(String message){
        this.message = message;
    }
}
