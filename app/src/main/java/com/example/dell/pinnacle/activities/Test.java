package com.example.dell.pinnacle.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.pinnacle.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class Test extends AppCompatActivity {
   int min = 1;
    public static  long ms = 0;
    TextView mTextField;
    ImageButton closeTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
        ms = (min*60)*1000;
         new CountDownTimer(ms, 1000) {

            public void onTick(long millisUntilFinished) {

                long minutes = (millisUntilFinished / 1000)  / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                mTextField.setText(minutes+":"+seconds);

            }


            public void onFinish() {
                mTextField.setText("Time's Up!");
                StyleableToast.makeText(Test.this, "Time's Up!!", Toast.LENGTH_LONG, R.style.mytoast).show();
            }
        }.start();
        closeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBox = new AlertDialog.Builder(Test.this);
                alertBox.setMessage("Are You Sure? Do You Want To Quit ?");
                alertBox.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       finish();
                        System.exit(0);
                    }
                });
                alertBox.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                    }
                });
                final AlertDialog alertDialog = alertBox.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.style.Dialog);
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.style.Dialog);
                    }
                });
                // show it
                alertDialog.show();


            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertBox = new AlertDialog.Builder(Test.this);
        alertBox.setMessage("Are You Sure? Do You Want To Quit ?");
        alertBox.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        alertBox.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = alertBox.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.style.Dialog);
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.style.Dialog);
            }
        });
        // show it
        alertDialog.show();



    }

    private void init() {
        mTextField  = findViewById(R.id.mTextField);
        closeTest = findViewById(R.id.closeTest);
    }
}
