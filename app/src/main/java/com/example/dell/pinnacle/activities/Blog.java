package com.example.dell.pinnacle.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.adaptors.BlogAdapter;
import com.example.dell.pinnacle.clients.ApiClient;
import com.example.dell.pinnacle.clients.RetroApi;
import com.example.dell.pinnacle.models.BlogDetails;
import com.example.dell.pinnacle.models.BlogModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Blog extends AppCompatActivity {
    private RecyclerView recyclerView;
    ProgressDialog pDialog;
    List<BlogDetails> blogDetailsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        recyclerView = findViewById(R.id.rcvidi);
        recyclerView.setHasFixedSize(true);

        pDialog = new ProgressDialog(Blog.this,R.style.MyTheme);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.setMax(100);
        pDialog.show();

        blogDetailsList = new ArrayList<>();

        ApiClient apiClient = RetroApi.getClient().create(ApiClient.class);
        Call<BlogModel> call =apiClient.blog();
        call.enqueue(new Callback<BlogModel>() {
            @Override
            public void onResponse(Call<BlogModel> call, Response<BlogModel> response) {
                BlogModel blogModel = response.body();
               List <BlogDetails> list =blogModel.getBlog();

               BlogDetails blogDetails = null;
                for (int i = 0; i < list.size(); i++){
                    blogDetails = new BlogDetails();
                    String blog = list.get(i).getBlog();

                    blogDetails.setBlog(blog);

                    blogDetailsList.add(blogDetails);
                }
                BlogAdapter recyclerAdapter = new BlogAdapter(getApplicationContext(),blogDetailsList);

                RecyclerView.LayoutManager rec = new LinearLayoutManager(getApplicationContext());

                recyclerView.setLayoutManager(rec);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<BlogModel> call, Throwable t) {
                Log.e("Error", t.getMessage(), t);
                Toast.makeText(Blog.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
