package com.example.dell.pinnacle.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.activities.GeneralStudies;
import com.example.dell.pinnacle.adaptors.VideoAdapter;
import com.example.dell.pinnacle.clients.ApiClient;
import com.example.dell.pinnacle.clients.RetroApi;
import com.example.dell.pinnacle.models.SubjectDetails;
import com.example.dell.pinnacle.models.SubjectsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralStudiesHindiVideos extends Fragment {
    private RecyclerView recyclerView;
    ProgressDialog pDialog;
    List<SubjectDetails> subjectDetailsList = new ArrayList<>();

    String subject_name,type,language,days;

    public GeneralStudiesHindiVideos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_general_studies_hindi_videos, container, false);

        subject_name = GeneralStudies.generalStudiesSubject;
        language = "hindi";
        type = "video";
        days = "0";

        recyclerView = view.findViewById(R.id.rcvidi);
        recyclerView.setHasFixedSize(true);

        pDialog = new ProgressDialog(getContext(),R.style.MyTheme);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.setMax(100);
        pDialog.show();

        subjectDetailsList = new ArrayList<>();

        ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
        Call<SubjectsModel> call = apiClient.getCards(language,type,days,subject_name);
        call.enqueue(new Callback<SubjectsModel>() {
            @Override
            public void onResponse(Call<SubjectsModel> call, Response<SubjectsModel> response) {
                SubjectsModel subjectsModel = response.body();
                List<SubjectDetails> list = subjectsModel.getDetail();

                SubjectDetails subjectDetails =null;
                for (int i = 0; i < list.size(); i++){
                    subjectDetails = new SubjectDetails();

                    String title = list.get(i).getTitle();
                    String description = list.get(i).getDececription();
                    String url = list.get(i).getUrl();

                    subjectDetails.setTitle(title);
                    subjectDetails.setDececription(description);
                    subjectDetails.setUrl(url);

                    subjectDetailsList.add(subjectDetails);
                }
                VideoAdapter recyclerAdapter = new VideoAdapter(getContext(),subjectDetailsList);
                RecyclerView.LayoutManager rec = new LinearLayoutManager(getContext());

                recyclerView.setLayoutManager(rec);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SubjectsModel> call, Throwable t) {
                Log.e("Error", t.getMessage(), t);
                Toast.makeText(getActivity(), "Something went wrong...", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });
        return view;
    }

}
