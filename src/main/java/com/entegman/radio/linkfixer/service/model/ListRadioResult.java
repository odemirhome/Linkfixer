package com.entegman.radio.linkfixer.service.model;

import com.entegman.radio.linkfixer.model.RadioForGrid;

import java.util.List;

public class ListRadioResult {
    public boolean success;
    public int total;

    public List<RadioForGrid> resultObject;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RadioForGrid> getResultObject() {
        return resultObject;
    }

    public void setResultObject(List<RadioForGrid> resultObject) {
        this.resultObject = resultObject;
    }
}
