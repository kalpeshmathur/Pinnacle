package com.example.dell.pinnacle.clients;

import com.example.dell.pinnacle.models.BlogModel;
import com.example.dell.pinnacle.models.ChangePasswordModel;
import com.example.dell.pinnacle.models.CompareOTP;
import com.example.dell.pinnacle.models.ForgotPasswordModel;
import com.example.dell.pinnacle.models.LoginModel;
import com.example.dell.pinnacle.models.NewPasswordModel;
import com.example.dell.pinnacle.models.OTPModel;
import com.example.dell.pinnacle.models.SignupModel;
import com.example.dell.pinnacle.models.SubjectsModel;
import com.example.dell.pinnacle.models.UpdateProfileModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by dell on 2/1/2018.
 */

public interface ApiClient {


    @POST("signup.php")
    @FormUrlEncoded
    Call<SignupModel>getRegistration(@Field("name") String name,
                                     @Field("mobile") String mobile,
                                     @Field("password") String password,
                                     @Field("email") String email,
                                     @Field("course_type") String course_type);
    @POST("login.php")
    @FormUrlEncoded
    Call<LoginModel> getLogin(@Field("mobile") String mobile,
                              @Field("password") String password);

    @POST("update_profile.php")
    @FormUrlEncoded
    Call<UpdateProfileModel> getUpdateProfile(@Field("name") String name,
                                              @Field("mobile") String mobile,
                                              @Field("email") String email);

    @Headers({
            "Accept: application/json",
    })


    @POST("send_otp.php")
    @FormUrlEncoded
    Call<OTPModel> getOTP(@Field("mobile") String mobile);

    @POST("forgot_password.php")
    @FormUrlEncoded
    Call<NewPasswordModel> getnewpassword(@Field("mobile") String mobile,
                                          @Field("otp") String otp,
                                          @Field("password") String password);

    @POST("json_apis/where.php")
    @FormUrlEncoded
    Call<SubjectsModel>getCards(@Field("language") String language,
                                @Field("type") String type,
                                @Field("days") String days,
                                @Field("subject_name") String subject_name);


    @Headers({
            "Accept: application/json",
    })
    @GET("json_apis/blog_json.php")
    Call<BlogModel> blog();

    @POST("compare_otp.php")
    @FormUrlEncoded
    Call<CompareOTP>matchOtp(@Field("otp") String type,
                             @Field("mobile") String language);

    @POST("change_password.php")
    @FormUrlEncoded
    Call<ChangePasswordModel>changePassword(@Field("new_password") String new_password,
                                            @Field("old_password") String old_password,
                                            @Field("mobile") String mobile);

    @POST("forgot_password.php")
    @FormUrlEncoded
    Call<ForgotPasswordModel>forgotPassword(@Field("password") String password,
                                            @Field("mobile") String mobile,
                                            @Field("otp") String otp);

}
