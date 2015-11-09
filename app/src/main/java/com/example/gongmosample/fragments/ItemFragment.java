package com.example.gongmosample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

public class ItemFragment extends Fragment {

    ImageView mImageView;

    public static ItemFragment newInstance() {
        return new ItemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



}
