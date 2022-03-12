package com.amazon.stock.response;

import com.amazon.stock.helpers.Helper;

import java.io.Serializable;

public class ResponseBase implements Serializable {
    public Meta meta = new Meta(Helper.HELPER.constant.HTTP_SUCCES);
}
