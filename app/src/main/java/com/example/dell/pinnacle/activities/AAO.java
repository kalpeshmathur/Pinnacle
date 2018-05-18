package com.example.dell.pinnacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.example.dell.pinnacle.R;

public class AAO extends AppCompatActivity {

//var declarations
    public static int day;
    private LinearLayout one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,ninteen,twenty,twenty_one,twenty_two,twenty_three,twenty_four,twenty_five,twenty_six;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aao);
        init();


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 1;
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 2;
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 3;
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 4;
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 5;
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 6;
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 7;
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 8;
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 9;
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 10;
            }
        });
        eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 11;
            }
        });
        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 12;
            }
        });
        thirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 13;
            }
        });
        fourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 14;
            }
        });
        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 15;
            }
        });
        sixteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 16;
            }
        });
        seventeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 17;
            }
        });
        eighteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 18;
            }
        });
        ninteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 19;
            }
        });
        twenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 20;
            }
        });
        twenty_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 21;
            }
        });
        twenty_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 22;
            }
        });
        twenty_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 23;
            }
        });
        twenty_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 24;
            }
        });
        twenty_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 25;
            }
        });
        twenty_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AAO.this, AAOMaterial.class));
                day = 26;
            }
        });
    }

    private void init() {
        one = findViewById(R.id.dayOne);
        two = findViewById(R.id.dayTwo);
        three = findViewById(R.id.dayThree);
        four = findViewById(R.id.dayFour);
        five = findViewById(R.id.dayFive);
        six = findViewById(R.id.daySix);
        seven = findViewById(R.id.daySeven);
        eight = findViewById(R.id.dayEight);
        nine = findViewById(R.id.dayNine);
        ten = findViewById(R.id.dayTen);
        eleven = findViewById(R.id.dayEleven);
        twelve = findViewById(R.id.dayTwelve);
        thirteen = findViewById(R.id.dayThirteen);
        fourteen =findViewById(R.id.dayFourteen);
        fifteen = findViewById(R.id.dayFifteen);
        sixteen = findViewById(R.id.daySixteen);
        seventeen = findViewById(R.id.daySeventeen);
        eighteen = findViewById(R.id.dayEighteen);
        ninteen = findViewById(R.id.dayNineteen);
        twenty = findViewById(R.id.dayTwenty);
        twenty_one = findViewById(R.id.dayTwentyOne);
        twenty_two = findViewById(R.id.dayTwentyTwo);
        twenty_three = findViewById(R.id.dayTwentyThree);
        twenty_four = findViewById(R.id.dayTwentyFour);
        twenty_five = findViewById(R.id.dayTwentyFive);
        twenty_six =  findViewById(R.id.dayTwentySix);

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
