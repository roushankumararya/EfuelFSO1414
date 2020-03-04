package com.developtech.efuelfo.model;

/**
 * Created by dt on 1/9/18.
 */

public class ResultModel<T> {
    String code= null;
    String message = null;
    T data = null;

    public String getResultCode() {
        return code;
    }

    public void setResultCode(String resultCode) {
        this.code= resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResutData() {
        return data;
    }

    public void setResutData(T resutData) {
        this.data = resutData;
    }
}
