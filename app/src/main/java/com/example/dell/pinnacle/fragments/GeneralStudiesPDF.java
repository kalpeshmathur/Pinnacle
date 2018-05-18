package com.example.dell.pinnacle.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.activities.GeneralStudies;
import com.example.dell.pinnacle.activities.PDF;
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
public class GeneralStudiesPDF extends Fragment {
    String pdfurl;
    ListView listView;
    ArrayList<String> pdfdata;
    String subject_name,type,language,days;
    List<SubjectDetails> list = new ArrayList<>();


    public GeneralStudiesPDF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_general_studies_pdf, container, false);
        listView = view.findViewById(R.id.listViewGn);

        pdfdata=new ArrayList<>();

        subject_name = GeneralStudies.generalStudiesSubject;
        language = "english";
        type = "pdf";
        days = "0";

        ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
        Call<SubjectsModel> call = apiClient.getCards(language,type,days,subject_name);
        call.enqueue(new Callback<SubjectsModel>() {
            @Override
            public void onResponse(Call<SubjectsModel> call, Response<SubjectsModel> response) {
                SubjectsModel subjectsModel = response.body();
                list = subjectsModel.getDetail();

                for (int i = 0;i<list.size();i++){

                    pdfdata.add(list.get(i).getTitle());

                }
                listView.setAdapter(new ArrayAdapter<String>(
                        getContext(),android.R.layout.simple_list_item_1,pdfdata
                ));

                registerForContextMenu(listView);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pdfurl= list.get(position).getUrl();
                        // Toast.makeText(getActivity(), pdfurl, Toast.LENGTH_SHORT).show();
                        Bundle bn = new Bundle();
                        bn.putString("pdfUrl",pdfurl);
                        Intent intent = new Intent(getActivity(), PDF.class);
                        intent.putExtras(bn);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<SubjectsModel> call, Throwable t) {
                Log.e("Error", t.getMessage(), t);
                Toast.makeText(getActivity(), "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
