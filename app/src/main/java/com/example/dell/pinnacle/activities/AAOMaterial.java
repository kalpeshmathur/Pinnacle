package com.example.dell.pinnacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.fragments.AAOHindiVideos;
import com.example.dell.pinnacle.fragments.AAOEnglishVideos;
import com.example.dell.pinnacle.fragments.AAOPDF;

import java.util.ArrayList;
import java.util.List;

public class AAOMaterial extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aao_material);
        if (AAO.day==1){
            setTitle("Day One");
        }else  if (AAO.day==2){
            setTitle("Day Two");
        }else  if (AAO.day==3){
            setTitle("Day Three");
        }else  if (AAO.day==4){
            setTitle("Day Four");
        }else  if (AAO.day==5){
            setTitle("Day Five");
        }else  if (AAO.day==6){
            setTitle("Day Six");
        }else  if (AAO.day==7){
            setTitle("Day Seven");
        }else  if (AAO.day==8){
            setTitle("Day Eight");
        }else  if (AAO.day==9){
            setTitle("Day Nine");
        }else  if (AAO.day==10){
            setTitle("Day Ten");
        }else  if (AAO.day==11){
            setTitle("Day Eleven");
        }else  if (AAO.day==12){
            setTitle("Day Twelve");
        }else  if (AAO.day==13){
            setTitle("Day Thirteen");
        }else  if (AAO.day==14){
            setTitle("Day Fourteen");
        }else  if (AAO.day==15){
            setTitle("Day Fifteen");
        }else  if (AAO.day==16){
            setTitle("Day Sixteen");
        }else  if (AAO.day==17){
            setTitle("Day Seventeen");
        }else  if (AAO.day==18){
            setTitle("Day Eighteen");
        }else  if (AAO.day==19){
            setTitle("Day Nineteen");
        }else  if (AAO.day==20){
            setTitle("Day Twenty");
        }else  if (AAO.day==21){
            setTitle("Day Twenty One");
        }else  if (AAO.day==22){
            setTitle("Day Twenty Two");
        }else  if (AAO.day==23){
            setTitle("Day Twenty Three");
        }else  if (AAO.day==24){
            setTitle("Day Twenty Four");
        }else  if (AAO.day==25){
            setTitle("Day Twenty Five");
        }else  if (AAO.day==26){
            setTitle("Day Twenty Six");
        }else{
            setTitle("AAO");
        }
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabLayOut);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AAOHindiVideos(),"Bilingual Videos");
        adapter.addFragment(new AAOEnglishVideos(),"English Videos");
        adapter.addFragment(new AAOPDF(),"PDF");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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
        startActivity(new Intent(AAOMaterial.this,AAO.class));
    }
}
