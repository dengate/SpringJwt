package com.amazon.stock.service;

import com.amazon.stock.enums.ErrorCode;
import com.amazon.stock.enums.ErrorMessage;
import com.amazon.stock.response.Meta;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Functions {
    public Meta createSuccessMeta(){
        Meta meta = new Meta();
        meta.errorCode = ErrorCode.HTTP_SUCCESS.code;
        meta.errorDesc = ErrorMessage.SUCCESS.message;
        return meta;
    }
    public Meta createErrorMeta(){
        Meta meta = new Meta();
        meta.errorCode = ErrorCode.HTTP_ERROR.code;
        meta.errorDesc = ErrorMessage.ERROR.message;
        return meta;
    }
    public String now(String format) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
