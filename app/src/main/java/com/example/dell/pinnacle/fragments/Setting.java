package com.example.dell.pinnacle.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.dell.pinnacle.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Setting extends Fragment {

    private LinearLayout myProfile,editProfile,changePassword;
    public Setting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        getActivity().setTitle("Profile Setting");
        myProfile = view.findViewById(R.id.myProfile);
        editProfile = view.findViewById(R.id.editProfile);
        changePassword = view.findViewById(R.id.changePassword);
        AboutUs.hideKeyboard(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MyProfile();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment,fragment).commit();
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new EditProfile();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment,fragment).commit();
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChangePassword();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment,fragment).commit();
            }
        });
    }
}
