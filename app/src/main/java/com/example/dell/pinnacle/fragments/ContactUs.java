package com.example.dell.pinnacle.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.pinnacle.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs extends Fragment {
    TextView contactUsEmail,contactUsPhone,contactUsWebsite,contactUsMap;
    String number="9729327755";
    String website ="ssccglpinnacle.com";
   // String map = "https://g.co/kgs/Q9nVNE";
    public ContactUs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_contact_us, container, false);
        getActivity().setTitle("Contact Us");
        contactUsEmail = view.findViewById(R.id.contactUsEmail);
        contactUsPhone = view.findViewById(R.id.contactUsPhone);
        contactUsWebsite = view.findViewById(R.id.contactUsWebsite);
        contactUsMap = view.findViewById(R.id.contactUsMap);
        AboutUs.hideKeyboard(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contactUsEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"support@ssccglpinnacle.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Query");
                i.putExtra(Intent.EXTRA_TEXT   , "Hi Pinnacle,");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        contactUsPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+number ));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.CALL_PHONE))
                    {
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},300);
                    }
                    return;
                }
                startActivity(intent);
            }
        });
        contactUsWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+website));
                startActivity(intent);
            }
        });
        contactUsMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://"+map));
                startActivity(intent);*/
            }
        });
    }
}
