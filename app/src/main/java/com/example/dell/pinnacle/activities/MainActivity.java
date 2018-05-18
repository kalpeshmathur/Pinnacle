package com.example.dell.pinnacle.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dell.pinnacle.fragments.AboutUs;
import com.example.dell.pinnacle.fragments.ContactUs;
import com.example.dell.pinnacle.fragments.EditProfile;
import com.example.dell.pinnacle.fragments.Home;
import com.example.dell.pinnacle.fragments.MyCourse;
import com.example.dell.pinnacle.fragments.Notifications;
import com.example.dell.pinnacle.fragments.Setting;
import com.example.dell.pinnacle.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView textCartItemCount;
    int mCartItemCount = 10;
    public static int click = 1;

    SharedPreferences sp;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       // navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            View r = findViewById(android.R.id.content);
            //r.setBackgroundResource(R.color.splashColor);
            Snackbar.make(r,"Please click BACK again to exit",Snackbar.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);

            //simple back
            /*super.onBackPressed();*/

            /*//same activity
            startActivity(new Intent(MainActivity.this,MainActivity.class));*/


            //it will close the app
            /*Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);*/
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
      /*  MenuItem counter = menu.findItem(R.id.notifications);
        counter.setActionView(R.layout.custom_action_item_layout);
       *//* TextView textView = findViewById(R.id.cart_badge);
        textView.setText(mCartItemCount);*//*
        FrameLayout frameLayout = (FrameLayout) MenuItemCompat.getActionView(counter);
        setupBadge();*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.notifications) {
            click++;
            if (click%2==0){
                Fragment fragment = new Notifications();
                FragmentManager fragmentManager= getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment,fragment).addToBackStack("MainActivity").commit();

            }
            if (click%2!=0){
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStackImmediate();
            }

        }

        return super.onOptionsItemSelected(item);
    }
  /*  private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.slideHome) {

            fragment = new Home();
            FragmentManager manager= getFragmentManager();
            manager.beginTransaction().replace(R.id.fragment,fragment).commit();

        } else if (id == R.id.myProfile) {
            fragment = new EditProfile();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment,fragment).commit();

        } else if (id == R.id.myCourse) {
            fragment = new MyCourse();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment,fragment).addToBackStack("MainActivity").commit();

        } else if (id == R.id.aboutUs) {
            fragment = new AboutUs();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment,fragment).commit();

        } else if (id == R.id.contactUs) {
            fragment = new ContactUs();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment,fragment).commit();

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "APP URL");
            startActivity(Intent.createChooser(intent, "Pinnacle App"));
        }else if (id==R.id.setting){
            fragment = new Setting();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment,fragment).addToBackStack("MainActivity").commit();

        }else if (id==R.id.logout){
            sp = getSharedPreferences("pref",MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit().clear();
            ed.commit();

            Intent intent = new Intent(MainActivity.this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
