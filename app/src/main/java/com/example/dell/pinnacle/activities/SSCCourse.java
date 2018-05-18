package com.example.dell.pinnacle.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dell.pinnacle.R;

public class SSCCourse extends AppCompatActivity {
    private LinearLayout tierOnessc,tierTwossc,tierThreessc,tierFourssc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssccourse);
        init();
        tierOnessc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SSCCourse.this, "Tier-1", Toast.LENGTH_SHORT).show();
            }
        });
        tierTwossc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SSCCourse.this, "Tier-2", Toast.LENGTH_SHORT).show();
            }
        });
        tierThreessc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SSCCourse.this, "Tier-3", Toast.LENGTH_SHORT).show();
            }
        });
        tierFourssc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SSCCourse.this, "Tier-4", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        tierOnessc = findViewById(R.id.sscOne);
        tierTwossc = findViewById(R.id.sscTwo);
        tierThreessc = findViewById(R.id.sscThree);
        tierFourssc = findViewById(R.id.sscFour);
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
