package com.example.dell.pinnacle.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.example.dell.pinnacle.activities.MainActivity;
import com.example.dell.pinnacle.adaptors.OnSwipeTouchListener;
import com.example.dell.pinnacle.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notifications extends Fragment{

   public LinearLayout linearLayout;

    public Notifications() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        linearLayout = view.findViewById(R.id.gesturelistner);
      final Animation slide_down =new AlphaAnimation(1.00f,0.00f);
      slide_down.setDuration(700);

         /* final Animation slide_up = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_up);*/
            linearLayout.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {

            public void onSwipeTop() {
                MainActivity.click++;
                linearLayout.startAnimation(slide_down);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStackImmediate();

            }
        });
        AboutUs.hideKeyboard(getActivity());
        return view;

    }
}
