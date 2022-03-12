package com.amazon.stock.exception;

import com.amazon.stock.enums.ErrorCode;
import com.amazon.stock.enums.ErrorMessage;
import com.amazon.stock.response.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationErrorException.class)
    public ResponseEntity<Object> handleValidationErrorException(ValidationErrorException exception, WebRequest webRequest) {
        ResponseBase response = new ResponseBase();
        response.meta.errorCode = ErrorCode.VALIDATION_ERROR.code;
        response.meta.errorDesc = ErrorMessage.VALIDATION_ERROR.message;
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(SaveEntityErrorException.class)
    public ResponseEntity<Object> handleSaveEntityErrorException(SaveEntityErrorException exception, WebRequest webRequest) {
        ResponseBase response = new ResponseBase();
        response.meta.errorCode = ErrorCode.SAVE_ENTITY_ERROR.code;
        response.meta.errorDesc = ErrorMessage.SAVE_ENTITY_ERROR.message;
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @ExceptionHandler(GeneralErrorException.class)
    public ResponseEntity<Object> handleGeneralErrorException(GeneralErrorException exception, WebRequest webRequest) {
        ResponseBase response = new ResponseBase();
        response.meta.errorCode = ErrorCode.HTTP_ERROR.code;
        response.meta.errorDesc = ErrorMessage.ERROR.message;
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException exception, WebRequest webRequest) {
        ResponseBase response = new ResponseBase();
        response.meta.errorCode = ErrorCode.CATEGORY_NOT_FOUND.code;
        response.meta.errorDesc = ErrorMessage.CATEGORY_NOT_FOUND.message;
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(EmptyTableException.class)
    public ResponseEntity<Object> handleEmptyTableException(EmptyTableException exception, WebRequest webRequest) {
        ResponseBase response = new ResponseBase();
        response.meta.errorCode = ErrorCode.EMPTY_TABLE.code;
        response.meta.errorDesc = ErrorMessage.EMPTY_TABLE.message;
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception, WebRequest webRequest) {
        ResponseBase response = new ResponseBase();
        response.meta.errorCode = ErrorCode.PRODUCT_NOT_FOUND.code;
        response.meta.errorDesc = ErrorMessage.PRODUCT_NOT_FOUND.message;
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

}
