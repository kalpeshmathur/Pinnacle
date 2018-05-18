package com.example.dell.pinnacle.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.clients.ApiClient;
import com.example.dell.pinnacle.clients.RetroApi;
import com.example.dell.pinnacle.models.ChangePasswordModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePassword extends Fragment {
    Button change;
    String newPasswordText,confirmPasswordText,oldPasswordText;
    private EditText newPassword,confirmPassword,oldPassword;
    SharedPreferences sp;
    ProgressDialog pDialog;

    public ChangePassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_change_password, container, false);

        getActivity().setTitle("Change Password");

        change = view.findViewById(R.id.profileChangePassword);
        newPassword = view.findViewById(R.id.profileNewPassword);
        oldPassword = view.findViewById(R.id.profileOldPassword);
        confirmPassword = view.findViewById(R.id.profileConfirmPassword);
        RelativeLayout r = view.findViewById(R.id.r);

        oldPassword.setTransformationMethod(new PasswordTransformationMethod());
        newPassword.setTransformationMethod(new PasswordTransformationMethod());
        confirmPassword.setTransformationMethod(new PasswordTransformationMethod());


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        sp= getActivity().getSharedPreferences("pref",MODE_PRIVATE);
        final String M;
        M = sp.getString("mobile","");

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                newPasswordText = newPassword.getText().toString().trim();
                oldPasswordText = oldPassword.getText().toString().trim();
                confirmPasswordText = confirmPassword.getText().toString().trim();

                if (oldPasswordText.isEmpty()){
                    flag=1;
                    oldPassword.setError("Please enter the OTP");
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
                if (flag ==0){
                    pDialog = new ProgressDialog(getActivity(),R.style.MyTheme);
                    pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.setMax(100);
                    pDialog.show();
                    ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
                    Call<ChangePasswordModel> call = apiClient.changePassword(newPasswordText,oldPasswordText,M);
                    call.enqueue(new Callback<ChangePasswordModel>() {
                        @Override
                        public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                            if(response.body().getSuccess()==200){
                                Toast.makeText(getActivity(), "Password is Changed!!", Toast.LENGTH_SHORT).show();
                                pDialog.dismiss();
                                Fragment fragment = new Setting();
                                FragmentManager fragmentManager = getFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.fragment,fragment).commit();
                            }
                            if (response.body().getSuccess()==204){
                                Toast.makeText(getActivity(), "Old Password is not valid", Toast.LENGTH_SHORT).show();
                                pDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            pDialog.dismiss();
                        }
                    });
                }

            }
        });
    }
}
