package com.example.dell.pinnacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dell.pinnacle.R;

public class PowerOfFive extends AppCompatActivity {
    private LinearLayout narration,activePassiveVoice,commonError,paraJumble,clozeTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_of_five);
        init();
        narration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PowerOfFive.this,Narration.class));
            }
        });
        activePassiveVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PowerOfFive.this,ActivePassiveVoice.class));
            }
        });
        commonError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PowerOfFive.this,CommonError.class));
            }
        });
        paraJumble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PowerOfFive.this,ParaJumble.class));
            }
        });
        clozeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PowerOfFive.this,ClozeTest.class));
            }
        });
    }

    private void init() {
        narration = findViewById(R.id.narration);
        activePassiveVoice = findViewById(R.id.activePassiveVoice);
        commonError = findViewById(R.id.commonError);
        paraJumble = findViewById(R.id.paraJumble);
        clozeTest = findViewById(R.id.clozeTest);
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
        startActivity(new Intent(PowerOfFive.this,Grammar.class));
    }
}
