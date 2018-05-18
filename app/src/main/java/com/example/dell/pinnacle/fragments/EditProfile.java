package com.example.dell.pinnacle.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.pinnacle.clients.ApiClient;
import com.example.dell.pinnacle.clients.RetroApi;
import com.example.dell.pinnacle.models.UpdateProfileModel;
import com.example.dell.pinnacle.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfile extends Fragment {
    private Button btnSave;

    private EditText saveName,saveEmail;

    String name,email, namesp,emailsp;



    SharedPreferences sp;
    public EditProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        btnSave = view.findViewById(R.id.save);
        saveName = view.findViewById(R.id.saveName);
        saveEmail = view.findViewById(R.id.saveEmail);
        getActivity().setTitle("Edit Profile");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sp= getActivity().getSharedPreferences("pref",MODE_PRIVATE);
        final String M,N,E,C;
        M = sp.getString("mobile","");
        N = sp.getString("name","");
        E = sp.getString("email","");

        saveEmail.setText(E);
        saveName.setText(N);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = saveName.getText().toString().trim();
                email = saveEmail.getText().toString().trim();

                int flag = 0;

                if (name.isEmpty()){
                    flag=1;
                    saveName.setError("Please Fill This Field");
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    flag=1;
                    saveEmail.setError("Should be a Valid Email Address");
                }
                for (int i=0; i<name.length();i++) {
                    char c = name.charAt(i);

                    if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')||(c==' '))) {
                        flag = 1;
                        saveName.setError("Please enter a valid name");
                    }
                }
                if (flag==0){
                     ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
                    Call<UpdateProfileModel> call = apiClient.getUpdateProfile(name,M,email);
                    call.enqueue(new Callback<UpdateProfileModel>() {
                    @Override
                    public void onResponse(Call<UpdateProfileModel> call, Response<UpdateProfileModel> response) {
                        if(response.body().getSuccess()==200){
                            Toast.makeText(getActivity(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            Fragment fragment = new MyProfile();
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.fragment,fragment).commit();

                            namesp = response.body().getDetail().getName();
                            emailsp = response.body().getDetail().getEmail();

                            Log.d("name", namesp);
                            Log.d("email",emailsp);


                            offlineEdit();


                        }else {
                            Toast.makeText(getActivity(), "Can't Update...", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });
                }

               /* Fragment fragment = new Home();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment,fragment).commit();
                Toast.makeText(getActivity(), "Saved!!", Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    private void offlineEdit() {
        sp = getActivity().getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("name",name);
        ed.putString("email",email);
        ed.commit();
    }
}
