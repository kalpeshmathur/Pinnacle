package com.example.dell.pinnacle.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dell.pinnacle.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCourse extends Fragment {


    public MyCourse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_course, container, false);
        AboutUs.hideKeyboard(getActivity());
        getActivity().setTitle("My Course");
        return view;
    }

}
