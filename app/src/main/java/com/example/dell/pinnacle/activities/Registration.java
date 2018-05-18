package com.example.dell.pinnacle.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.clients.ApiClient;
import com.example.dell.pinnacle.clients.RetroApi;
import com.example.dell.pinnacle.models.CompareOTP;
import com.example.dell.pinnacle.models.SignupModel;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    private EditText name,mobile,email,password,repassword;
    private Button register,otp;
    private TextView loginText;
    public String nametext,mobiletext,emailtext,passwordtext,repasswordtext;
    public String course_type="free";

    public String code="";

    public static EditText regOTP;
    
    Context ctx;
    ApiClient apiClient;

    ProgressDialog pDialog;

    SmsVerifyCatcher smsVerifyCatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        StyleableToast.makeText(Registration.this, "Thanks For Choosing Us!", Toast.LENGTH_LONG, R.style.mytoast).show();

        init();

        smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
            @Override
            public void onSmsCatch(String message) {
                code = message.replaceAll("\\D+","");
                Log.d("sms",code);
                regOTP.setText(code);
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,Login.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
                nametext = name.getText().toString().trim();
                mobiletext=mobile.getText().toString().trim();
                emailtext=email.getText().toString().trim();
                passwordtext = password.getText().toString();
                repasswordtext = repassword.getText().toString();

                int flag = 0;

                if (nametext.isEmpty()){
                    flag=1;
                    name.setError("Please Fill This Field");
                }
                if (mobiletext.isEmpty()){
                    flag=1;
                    mobile.setError("Please Fill This Field");
                }
                if (emailtext.isEmpty()){
                    flag=1;
                    email.setError("Please Fill This Field");
                }
                if (passwordtext.isEmpty()){
                    flag=1;
                    password.setError("Please Fill This Field");
                }
                if (repasswordtext.isEmpty()){
                    flag=1;
                    repassword.setError("Please Fill This Field");
                }
                if (!passwordtext.equals(repasswordtext)){
                    flag = 1;
                    repassword.setError("this password does not match with other");
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()){
                    flag=1;
                    email.setError("Should be a Valid Email Address");
                }
                if (mobiletext.length()!=10&&mobiletext.length()!=0){
                    flag=1;
                    mobile.setError("please enter 10 digits");
                }
                for (int i =0;i<mobiletext.length();i++){
                    char c = mobiletext.charAt(i);
                    if (!(c >= '0' && c <= '9')) {
                        flag = 1;
                        mobile.setError("Please enter a valid Number");
                    }
                }
                if (passwordtext.length()<8&&passwordtext.length()!=0){
                    flag=1;
                    password.setError("Password Can not be less than 8 letters");
                }
                for (int i=0; i<nametext.length();i++) {
                    char c = nametext.charAt(i);

                    if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')||(c==' '))) {
                        flag = 1;
                        name.setError("Please enter a valid name");
                    }
                }
                if (flag==0){
                    pDialog = new ProgressDialog(Registration.this,R.style.MyTheme);
                    pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.setMax(100);
                    pDialog.show();

                    apiClient = RetroApi.getClient().create(ApiClient.class);
                    Call<SignupModel> call = apiClient.getRegistration(nametext,mobiletext,passwordtext,emailtext,course_type);
                    call.enqueue(new Callback<SignupModel>() {
                                     @Override
                                     public void onResponse(Call<SignupModel> call, Response<SignupModel> response) {
                                         if(response.isSuccessful()){

                                             pDialog.dismiss();
                                             Toast.makeText(Registration.this, "Registered", Toast.LENGTH_SHORT).show();
                                             final Dialog dialog = new Dialog(Registration.this);
                                             dialog.setContentView(R.layout.otp_box);
                                             otp = dialog.findViewById(R.id.go);
                                             regOTP = dialog.findViewById(R.id.regOTP);
                                             dialog.show();

                                             dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                 @Override
                                                 public void onDismiss(DialogInterface dialog) {
                                                     startActivity(new Intent(Registration.this,Login.class));
                                                     overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                                 }
                                             });
                                             otp.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     String otp = regOTP.getText().toString().trim();
                                                         if (!otp.isEmpty()){
                                                             pDialog = new ProgressDialog(Registration.this,R.style.MyTheme);
                                                             pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                                                             pDialog.setIndeterminate(false);
                                                             pDialog.setCancelable(false);
                                                             pDialog.setMax(100);
                                                             pDialog.show();

                                                             apiClient = RetroApi.getClient().create(ApiClient.class);
                                                             Call<CompareOTP> call1 =apiClient.matchOtp(otp,mobiletext);
                                                            call1.enqueue(new Callback<CompareOTP>() {
                                                             @Override
                                                             public void onResponse(Call<CompareOTP> call, Response<CompareOTP> response) {
                                                                 pDialog.dismiss();
                                                                 startActivity(new Intent(Registration.this,Login.class));
                                                                 overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

                                                                 dialog.dismiss();
                                                                 if (response.body().getSuccess()==200){
                                                                     Toast.makeText(Registration.this, "Verified", Toast.LENGTH_SHORT).show();
                                                                 }else {
                                                                     Toast.makeText(Registration.this, "OTP does not match", Toast.LENGTH_SHORT).show();
                                                                 }
                                                             }

                                                             @Override
                                                             public void onFailure(Call<CompareOTP> call, Throwable t) {
                                                                 dialog.dismiss();
                                                                 Toast.makeText(Registration.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                                                 startActivity(new Intent(Registration.this,Login.class));
                                                                 overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                                                             }
                                                         });
                                                     }
                                                 }
                                             });
                                         }
                                         else {
                                             pDialog.dismiss();
                                             Log.d("Register","else part is executing");
                                             View r = findViewById(android.R.id.content);
                                             Snackbar.make(r,"Number/Email already registered",Snackbar.LENGTH_LONG).show();
                                         }
                                     }
                                     @Override
                                     public void onFailure(Call<SignupModel> call, Throwable t) {
                                         pDialog.dismiss();
                                         View r = findViewById(android.R.id.content);
                                         Snackbar.make(r,"Something Went Wrong",Snackbar.LENGTH_LONG).show();
                                     }
                                 });
                }
            }
        });
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
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        loginText = findViewById(R.id.logintext);
        repassword = findViewById(R.id.repassword);
        password.setTransformationMethod(new PasswordTransformationMethod());
        repassword.setTransformationMethod(new PasswordTransformationMethod());

    }

}
