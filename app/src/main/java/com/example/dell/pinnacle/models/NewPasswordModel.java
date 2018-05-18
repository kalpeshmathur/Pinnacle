
package com.example.dell.pinnacle.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewPasswordModel {

    @SerializedName("detail")
    @Expose
    private NewPasswordDetail detail;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;

    public NewPasswordDetail getDetail() {
        return detail;
    }

    public void setDetail(NewPasswordDetail detail) {
        this.detail = detail;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
