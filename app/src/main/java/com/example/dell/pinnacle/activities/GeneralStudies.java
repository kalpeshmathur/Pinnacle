package com.example.dell.pinnacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dell.pinnacle.R;

public class GeneralStudies extends AppCompatActivity {
    private LinearLayout generalScience,geography,history,polity,economics,gsRevisionSeries;

    public static String generalStudiesSubject=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_studies);
        init();
        generalScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneralStudies.this, GeneralStudiesMaterial.class));
                generalStudiesSubject = "General Science";
            }
        });
        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneralStudies.this, GeneralStudiesMaterial.class));
                generalStudiesSubject = "Geography";
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneralStudies.this, GeneralStudiesMaterial.class));
                generalStudiesSubject = "History";

            }
        });
        polity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneralStudies.this, GeneralStudiesMaterial.class));
                generalStudiesSubject = "Polity";
            }
        });
        economics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneralStudies.this, GeneralStudiesMaterial.class));
                generalStudiesSubject = "Economics";
            }
        });
        gsRevisionSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneralStudies.this, GeneralStudiesMaterial.class));
                generalStudiesSubject = "GS Revision Series";
            }
        });
    }

    private void init() {
        generalScience = findViewById(R.id.generalScience);
        geography = findViewById(R.id.geography);
        history = findViewById(R.id.history);
        polity = findViewById(R.id.polity);
        economics = findViewById(R.id.economics);
        gsRevisionSeries=findViewById(R.id.gsRevisionSeries);
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
        Intent intent = new Intent(this, StudyMaterial.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
