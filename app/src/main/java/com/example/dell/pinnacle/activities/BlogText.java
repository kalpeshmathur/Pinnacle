package com.example.dell.pinnacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.pinnacle.R;

public class BlogText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_text);

        TextView textView = findViewById(R.id.text_of_blog);
        RelativeLayout relativeBlog = findViewById(R.id.relativeBlog);


        ImageButton imageView = findViewById(R.id.closeBlog);
       /* imageView.setVisibility(View.GONE);
        relativeBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        });*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String blogText = bundle.getString("blog");
        textView.setText(blogText);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BlogText.this, Blog.class));
    }
}
