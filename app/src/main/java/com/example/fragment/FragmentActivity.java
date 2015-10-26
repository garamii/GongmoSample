
package com.example.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gongmosample.R;


/**
 * Created by junsuk on 15. 9. 15..
 */
public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private ColorFragment mColorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mColorFragment = (ColorFragment) getSupportFragmentManager().findFragmentById(
                R.id.frag_hwa);

//        findViewById(R.id.frag_hwa).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        Uri uri = Uri.parse("http://www.naver.com");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }
}
