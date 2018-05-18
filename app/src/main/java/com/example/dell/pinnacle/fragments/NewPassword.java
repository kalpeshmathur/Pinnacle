package com.example.dell.pinnacle.fragments;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.activities.Login;
import com.example.dell.pinnacle.clients.ApiClient;
import com.example.dell.pinnacle.clients.RetroApi;
import com.example.dell.pinnacle.models.ForgotPasswordModel;
import com.example.dell.pinnacle.models.OTPModel;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPassword extends Fragment {
    private Button ok;
    String newPasswordText,confirmPasswordText,otptext;
    private TextView reSend;
    public static EditText otp;
    public String number;
    private EditText newPassword,confirmPassword;
    ProgressDialog pDialog;
    SmsVerifyCatcher smsVerifyCatcher;
    public String code="";
    public NewPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_password, container, false);
        ok = view.findViewById(R.id.ok);
        newPassword = view.findViewById(R.id.newPassword);
        newPassword.setTransformationMethod(new PasswordTransformationMethod());
        confirmPassword = view.findViewById(R.id.confirmPassword);
        confirmPassword.setTransformationMethod(new PasswordTransformationMethod());
        number = getArguments().getString("number");
        Log.d("number",number);
        otp = view.findViewById(R.id.otp);
        reSend = view.findViewById(R.id.reSend);
        getActivity().setTitle("New Password");



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        smsVerifyCatcher = new SmsVerifyCatcher(getActivity(), new OnSmsCatchListener<String>() {
            @Override
            public void onSmsCatch(String message) {
                code = message.replaceAll("\\D+","");
                Log.d("sms",code);
                otp.setText(code);

            }
        });

        reSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(getActivity(),R.style.MyTheme);
                pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.setMax(100);
                pDialog.show();

                ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
                Call<OTPModel> call1 = apiClient.getOTP(number);
                call1.enqueue(new Callback<OTPModel>() {
                    @Override
                    public void onResponse(Call<OTPModel> call, Response<OTPModel> response) {
                        pDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<OTPModel> call, Throwable t) {
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }
                });
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;

                newPasswordText=newPassword.getText().toString().trim();
                confirmPasswordText = confirmPassword.getText().toString().trim();
                otptext=otp.getText().toString().trim();

                if (otptext.isEmpty()){
                    flag=1;
                    otp.setError("Please enter the OTP");
                }
                if (newPasswordText.isEmpty()){
                    flag = 1;
                    newPassword.setError("please do not leave this field empty");
                }
                if (confirmPasswordText.isEmpty()){
                    flag = 1;
                    confirmPassword.setError("please do not leave this field empty");
                }
                if (newPasswordText.length()<8 && newPasswordText.length()!=0){
                    flag = 1;
                    newPassword.setError("Password can not be less than 8 characters");
                }
                if (!newPasswordText.equals(confirmPasswordText)){
                    flag = 1;
                    newPassword.setError("this password does not match with other");
                }
                if (flag==0){
                    pDialog = new ProgressDialog(getActivity(),R.style.MyTheme);
                    pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.setMax(100);
                    pDialog.show();
                    ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
                    Call<ForgotPasswordModel> call = apiClient.forgotPassword(newPasswordText,number,otptext);
                    call.enqueue(new Callback<ForgotPasswordModel>() {
                        @Override
                        public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {
                            if(response.body().getSuccess()==200){
                                Toast.makeText(getActivity(), "Password has been Changed", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(),Login.class));
                                pDialog.dismiss();
                            }else {
                                Toast.makeText(getActivity(), "OTP Does not Match!", Toast.LENGTH_SHORT).show();
                                pDialog.dismiss();
                            }
                        }
                        @Override
                        public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            pDialog.dismiss();
                        }
                    });
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        smsVerifyCatcher.onStop();
    }

}
