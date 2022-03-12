package com.amazon.stock.enums;

public enum ErrorCode {
    HTTP_SUCCESS(200),
    HTTP_ERROR(500),
    VALIDATION_ERROR(101),
    SAVE_ENTITY_ERROR(102),
    CATEGORY_NOT_FOUND(103),
    EMPTY_TABLE(104),
    PRODUCT_NOT_FOUND(105),
    HTTP_FORBİDDEN(403);

    public final int code;

    ErrorCode(int code){
        this.code = code;
    }
}
