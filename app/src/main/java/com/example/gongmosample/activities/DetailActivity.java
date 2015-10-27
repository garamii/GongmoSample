package com.example.gongmosample.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.gongmosample.R;
import com.example.gongmosample.fragments.FestivalDetailFragment;
import com.example.gongmosample.models.Festival;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private ViewPager mViewPager;
    private ArrayList<Festival> mFestivalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        if (getIntent() != null) {
            // 전체 데이터
            mFestivalList = getIntent().getParcelableArrayListExtra("list");
            Log.d(TAG, mFestivalList.size() + "");

            // 선택 아이템 번호
            int position = getIntent().getIntExtra("position", -1);

            mViewPager = (ViewPager) findViewById(R.id.view_pager);
            MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(adapter);

            // position 번째로 화면 이동
            mViewPager.setCurrentItem(position);
        }

    }

    // FragmentPagerAdapter
    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FestivalDetailFragment.newInstance(mFestivalList.get(position));
        }

        @Override
        public int getCount() {
            return mFestivalList.size();
        }

    }
}
