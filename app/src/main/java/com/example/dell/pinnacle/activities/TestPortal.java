package com.example.dell.pinnacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.pinnacle.R;

public class TestPortal extends AppCompatActivity {
    TextView mTextField;
    RelativeLayout englishQuiz, mathsQuiz, reasoningQuiz, generalScienceQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_portal);
        init();

        englishQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(TestPortal.this, Test.class));

            }
        });
        mathsQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestPortal.this, Test.class));
            }
        });
        reasoningQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestPortal.this, Test.class));
            }
        });
        generalScienceQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestPortal.this, Test.class));
            }
        });
    }

    private void init() {
        //mTextField  = findViewById(R.id.mTextField);
        englishQuiz = findViewById(R.id.englishQuiz);
        mathsQuiz = findViewById(R.id.mathsQuiz);
        reasoningQuiz = findViewById(R.id.reasoningQuiz);
        generalScienceQuiz = findViewById(R.id.generalScienceQuiz);

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
