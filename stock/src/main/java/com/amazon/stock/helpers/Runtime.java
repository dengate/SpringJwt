package com.amazon.stock.helpers;


import com.amazon.stock.response.Meta;

public class Runtime {
    public Meta generateMeta(int errorCode, String desc, String trace) {
        return new Meta(errorCode, desc, trace);
    }

    public Meta generateMeta(int errorCode, String desc) {

        return new Meta(errorCode, desc);
    }

    public Meta generateMeta(int errorCode) {
        return new Meta(errorCode);
    }

    public Meta generateMeta(Meta meta) {
        return new Meta(meta.errorCode, meta.errorDesc);
    }


}
