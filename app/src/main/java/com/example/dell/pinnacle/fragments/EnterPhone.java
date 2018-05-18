package com.example.dell.pinnacle.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.clients.ApiClient;
import com.example.dell.pinnacle.clients.RetroApi;
import com.example.dell.pinnacle.models.OTPModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnterPhone extends Fragment {
    private Button sendNumber;
    private EditText enterNumber;
    ProgressDialog pDialog;
    public EnterPhone() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_enter_phone, container, false);
        sendNumber = view.findViewById(R.id.sendNumber);
        enterNumber = view.findViewById(R.id.enterNumber);
        getActivity().setTitle("Enter Phone");
        return view;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sendNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                String number = enterNumber.getText().toString().trim();
                if (number.isEmpty()){
                    flag = 1;
                    enterNumber.setError("Please do not leave this field empty");
                }
                if (number.length()!=10){
                    flag = 1;
                    enterNumber.setError("please write 10 digits");
                }
                for (int i =0;i<number.length();i++){
                    char c = number.charAt(i);
                    if (!(c >= '0' && c <= '9')) {
                        flag = 1;
                        enterNumber.setError("Please enter a valid Number");
                    }
                }
                if (flag==0){
                    pDialog = new ProgressDialog(getActivity(),R.style.MyTheme);
                    pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.setMax(100);
                    pDialog.show();
                    Bundle bn = new Bundle();

                    ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
                    Call<OTPModel> call = apiClient.getOTP(number);
                    call.enqueue(new Callback<OTPModel>() {
                        @Override
                        public void onResponse(Call<OTPModel> call, Response<OTPModel> response) {
                            if(response.body().getSuccess()==200){
                                pDialog.dismiss();
                                bn.putString("number",number);

                                Fragment fragment = new NewPassword();
                                fragment.setArguments(bn);
                                FragmentManager manager = getFragmentManager();
                                manager.beginTransaction().replace(R.id.fragment,fragment).commit();

                            }else {
                                Toast.makeText(getActivity(), "Number is not Registered!", Toast.LENGTH_SHORT).show();
                                pDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<OTPModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                            pDialog.dismiss();

                        }
                    });
                }
            }
        });
    }

}

