package com.saka.myapplication.Models;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/6.
 */

public class BaseEntity<E> implements Serializable {
    private int error_code;
    private String reason;
    private E result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public E getResult() {
        return result;
    }

    public void setResult(E result) {
        this.result = result;
    }
}
