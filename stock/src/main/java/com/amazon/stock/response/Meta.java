package com.amazon.stock.response;

import java.io.Serializable;

public class Meta implements Serializable {
    public int errorCode;
    public String errorDesc;
    public String stackTrace;

    public Meta() {
    }

    public Meta(int errorCode) {
        this.errorCode = errorCode;
    }

    public Meta(int errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public Meta(int errorCode, String errorDesc, String stackTrace) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.stackTrace = stackTrace;
    }
}
