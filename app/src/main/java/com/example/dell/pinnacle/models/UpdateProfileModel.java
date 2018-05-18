
package com.example.dell.pinnacle.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileModel {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("detail")
    @Expose
    private UpdateProfileDetail detail;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public UpdateProfileDetail getDetail() {
        return detail;
    }

    public void setDetail(UpdateProfileDetail detail) {
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
