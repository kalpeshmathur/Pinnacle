package com.example.dell.pinnacle.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.clients.ApiClient;
import com.example.dell.pinnacle.clients.RetroApi;
import com.example.dell.pinnacle.fragments.ForgetPassword;
import com.example.dell.pinnacle.models.CompareOTP;
import com.example.dell.pinnacle.models.LoginModel;
import com.example.dell.pinnacle.models.OTPModel;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText editTextmobile,editTextpassword;
    private Button login;
    private TextView regtext,forgetpassword;
    ProgressDialog pDialog;
    public String mobile,password,name,email,course_type;
    SharedPreferences sp;
    boolean doubleBackToExitPressedOnce = false;
    SmsVerifyCatcher smsVerifyCatcher;
    public static EditText regOTP;
    public static String code="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
            @Override
            public void onSmsCatch(String message) {
                code = message.replaceAll("\\D+","");
                Log.d("sms",code);
                regOTP.setText(code);

            }
        });
        regtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registration.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                mobile = editTextmobile.getText().toString().trim();
                password = editTextpassword.getText().toString().trim();

                if (mobile.isEmpty()){
                    flag=1;
                    editTextmobile.setError("Please Fill This Field");
                }
                if (password.isEmpty()){
                    flag=1;
                    editTextpassword.setError("Please Fill This Field");
                }
                if (mobile.length()!=10){
                    flag=1;
                    editTextmobile.setError("please enter 10 digits");
                }
                for (int i =0;i<mobile.length();i++){
                    char c = mobile.charAt(i);
                    if (!(c >= '0' && c <= '9')) {
                        flag = 1;
                        editTextmobile.setError("Please enter a valid Number");
                    }
                }
                if (password.length()<8){
                    flag=1;
                    editTextpassword.setError("Password Can not be less than 8 letters");
                }
                if (flag==0){

                    pDialog = new ProgressDialog(Login.this,R.style.MyTheme);
                    pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.setMax(100);
                    pDialog.show();

                    ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
                    Call<LoginModel> call = apiClient.getLogin(mobile,password);
                    call.enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                            if(response.body().getSuccess()==200){
                                name=response.body().getDetail().getName();
                                email=response.body().getDetail().getEmail();
                                course_type=response.body().getDetail().getCourse_type();

                                Log.d("Login","if part is running "+name);

                                sp = getSharedPreferences("pref",MODE_PRIVATE);
                                SharedPreferences.Editor ed = sp.edit();
                                ed.putString("mobile",mobile);
                                ed.putString("password",password);
                                ed.putString("name",name);
                                ed.putString("email",email);
                                ed.putString("course_type",course_type);
                                ed.commit();
                                Toast.makeText(Login.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                StyleableToast.makeText(Login.this, "Welcome!", Toast.LENGTH_LONG, R.style.mytoast).show();
                                startActivity(new Intent(Login.this,MainActivity.class));
                                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                                finish();
                                pDialog.dismiss();

                            }
                            if (response.body().getSuccess()==203||response.body().getSuccess()==204){
                                View r = findViewById(android.R.id.content);
                                Snackbar.make(r,"Username/Password is not correct",Snackbar.LENGTH_LONG).show();
                                pDialog.dismiss();
                            }
                            if (response.body().getSuccess()==205){
                                Toast.makeText(Login.this, "Not Verified", Toast.LENGTH_SHORT).show();

                                pDialog.dismiss();
                                final Dialog dialog = new Dialog(Login.this);
                                dialog.setContentView(R.layout.otp_box);
                                Button otp = dialog.findViewById(R.id.go);
                                regOTP = dialog.findViewById(R.id.regOTP);
                                dialog.setCancelable(false);
                                dialog.show();

                                ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
                                Call<OTPModel> call1=apiClient.getOTP(mobile);
                                call1.enqueue(new Callback<OTPModel>() {
                                    @Override
                                    public void onResponse(Call<OTPModel> call, Response<OTPModel> response) {
                                        otp.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String otp = regOTP.getText().toString().trim();
                                                if (!otp.isEmpty()){
                                                    pDialog = new ProgressDialog(Login.this,R.style.MyTheme);
                                                    pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                                                    pDialog.setIndeterminate(false);
                                                    pDialog.setCancelable(false);
                                                    pDialog.setMax(100);
                                                    pDialog.show();
                                                    ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
                                                    Call<CompareOTP> call2 =apiClient.matchOtp(otp,mobile);
                                                    call2.enqueue(new Callback<CompareOTP>() {
                                                        @Override
                                                        public void onResponse(Call<CompareOTP> call, Response<CompareOTP> response) {

                                                            sp = getSharedPreferences("pref",MODE_PRIVATE);
                                                            SharedPreferences.Editor ed = sp.edit();
                                                            ed.putString("mobile",mobile);
                                                            ed.putString("password",password);
                                                            ed.putString("name",name);
                                                            ed.putString("email",email);
                                                            ed.putString("course_type",course_type);
                                                            ed.commit();

                                                            dialog.dismiss();
                                                            pDialog.dismiss();

                                                            startActivity(new Intent(Login.this,MainActivity.class));
                                                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                                            StyleableToast.makeText(Login.this, "Welcome!", Toast.LENGTH_LONG, R.style.mytoast).show();
                                                        }

                                                        @Override
                                                        public void onFailure(Call<CompareOTP> call, Throwable t) {
                                                            Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Call<OTPModel> call, Throwable t) {
                                        View r = findViewById(android.R.id.content);
                                        Snackbar.make(r,"Username/Password is not correct",Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {
                            View r = findViewById(android.R.id.content);
                            pDialog.dismiss();
                            Snackbar.make(r,"SomeThing went wrong",Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgetPassword.class));
            }
        });

    }
    @Override
    public void onBackPressed() {


            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;

            View r = findViewById(android.R.id.content);
            Snackbar.make(r,"Please click BACK again to exit",Snackbar.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
    }
    @Override
    protected void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        smsVerifyCatcher.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void init() {
        editTextmobile = findViewById(R.id.editTextMobile);
        editTextpassword = findViewById(R.id.editTextPassword);
        editTextpassword.setTransformationMethod(new PasswordTransformationMethod());
        login = findViewById(R.id.login);
        regtext = findViewById(R.id.regtext);
        forgetpassword = findViewById(R.id.forgetpassword);
        }

}
