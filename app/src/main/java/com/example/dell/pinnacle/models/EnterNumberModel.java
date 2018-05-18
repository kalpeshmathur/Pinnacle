
package com.example.dell.pinnacle.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnterNumberModel {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("detail")
    @Expose
    private EnterNumberDetail detail;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public EnterNumberDetail getDetail() {
        return detail;
    }

    public void setDetail(EnterNumberDetail detail) {
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
