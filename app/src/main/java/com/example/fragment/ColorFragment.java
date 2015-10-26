
package com.example.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.gongmosample.R;


/**
 * Created by junsuk on 15. 9. 15.. 생성시 랜덤한 색깔이 적용되는 Fragment
 */public class ColorFragment extends Fragment implements View.OnClickListener {

    private ImageView mImageView;

    // View를 만드는 곳
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pic, container, false);
        mImageView = (ImageView) view.findViewById(R.id.iv_image);
        mImageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("http://www.naver.com");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }
}