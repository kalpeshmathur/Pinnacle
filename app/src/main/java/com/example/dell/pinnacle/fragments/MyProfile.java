package com.example.dell.pinnacle.fragments;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.pinnacle.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfile extends Fragment {
    TextView textViewName,textViewMobile,textViewEmail,textViewCourseType;

    SharedPreferences sp;
    public MyProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);
        getActivity().setTitle("My Profile");
        textViewName = view.findViewById(R.id.textViewName);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        textViewMobile = view.findViewById(R.id.textViewPhone);
        textViewCourseType=view.findViewById(R.id.textViewCourseType);
        AboutUs.hideKeyboard(getActivity());
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
        C = sp.getString("course_type","");

        textViewName.setText(N);
        textViewEmail.setText(E);
        textViewMobile.setText(M);
        textViewCourseType.setText(C);
    }
}
