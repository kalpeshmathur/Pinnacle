package com.example.dell.pinnacle.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.dell.pinnacle.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {

    public AboutUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about_us, container, false);
        getActivity().setTitle("About Us");
        //showSoftwareKeyboard(true);
        hideKeyboard(getActivity());
        return view;
    }
/*    @Override
    public void onDetach() {
        super.onDetach();

        //hide keyboard when any fragment of this class has been detached
        showSoftwareKeyboard(false);
    }

    private void showSoftwareKeyboard(boolean showKeyboard) {
        final Activity activity = getActivity();
        final InputMethodManager inputManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), showKeyboard ? InputMethodManager.SHOW_FORCED : InputMethodManager.HIDE_NOT_ALWAYS);
    }*/
public static void hideKeyboard(Activity activity) {
    InputMethodManager inputManager = (InputMethodManager) activity
            .getSystemService(Context.INPUT_METHOD_SERVICE);

    // check if no view has focus:
    View currentFocusedView = activity.getCurrentFocus();
    if (currentFocusedView != null) {
        inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}

}
