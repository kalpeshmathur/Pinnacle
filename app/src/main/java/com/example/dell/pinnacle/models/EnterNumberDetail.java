
package com.example.dell.pinnacle.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnterNumberDetail {

    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("otp_sent")
    @Expose
    private String otpSent;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getOtpSent() {
        return otpSent;
    }

    public void setOtpSent(String otpSent) {
        this.otpSent = otpSent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
