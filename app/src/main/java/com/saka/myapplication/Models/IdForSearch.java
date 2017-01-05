package com.saka.myapplication.Models;

import android.databinding.BaseObservable;

/**
 * Created by saka on 2017/1/2.
 */

public class IdForSearch extends BaseObservable{

    private int resultcode;
    private int error_code;
    private String reason;
    private CardInfo result;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

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

    public CardInfo getResult() {
        return result;
    }

    public void setResult(CardInfo result) {
        this.result = result;
    }

    public IdForSearch() {
    }



}
