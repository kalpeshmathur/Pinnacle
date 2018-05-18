package com.example.dell.pinnacle.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.view.WindowManager;
/*
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
*/
import com.daimajia.androidanimations.library.Techniques;
import com.example.dell.pinnacle.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashActivity extends AwesomeSplash{
    SharedPreferences sp;

    @Override
    public void initSplash(ConfigSplash configSplash) {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Background Animation
        configSplash.setBackgroundColor(R.color.btnclr);
        configSplash.setAnimCircularRevealDuration(2000);
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);

        //Logo Splash
        configSplash.setLogoSplash(R.drawable.splashtwo);
        configSplash.setAnimLogoSplashDuration(1000);
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeInUp);

        //Title Splash
        configSplash.setTitleSplash("www.ssccglpinnacle.com");
        configSplash.setTitleTextColor(R.color.colorAccent);
        configSplash.setTitleTextSize(25f);
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.BounceInUp);
    }

    @Override
    public void animationsFinished() {
        sp = getSharedPreferences("pref",MODE_PRIVATE);
        final String E,P;
        E = sp.getString("mobile","");
        P = sp.getString("password","");
        if (E.isEmpty()||P.isEmpty()) {
            startActivity(new Intent(SplashActivity.this, Login.class));
        }else {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        }
    }
}
/*
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       */
/* new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,Login.class));
                finish();
            }
        },2000);*//*


        ImageView imageview_logo = (ImageView) findViewById(R.id.imageview_logo);

        Animation animanimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_animation);

        imageview_logo.startAnimation(animanimation);

        animanimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //Toast.makeText(SplashActivity.this, "animation ", Toast.LENGTH_SHORT).show();
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,Login.class));
                        finish();
                    }
                },2000);

            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
    }
*/
