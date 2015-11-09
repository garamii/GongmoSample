package com.example.gongmosample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.gongmosample.R;
import com.example.gongmosample.models.Festival;
import com.example.gongmosample.volley.LruBitmapCache;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FestivalDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FestivalDetailFragment extends Fragment {
    private static final String ARG_FESTIVAL = "festival";

    private Festival mFestival;
    private ImageView imageView;

    // 이미지 가져오는 준비
    private RequestQueue mQueue;
    private ImageLoader mImageLoader;

    public FestivalDetailFragment() {
        // Required empty public constructor
    }

    public static FestivalDetailFragment newInstance(Festival festival) {
        FestivalDetailFragment fragment = new FestivalDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_FESTIVAL, festival);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mFestival = getArguments().getParcelable(ARG_FESTIVAL);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_festival_detail, container, false);
        //썸네일 이미지 http://culture.suwon.go.kr/common-upload +

        mQueue = Volley.newRequestQueue(getActivity());
        mImageLoader = new ImageLoader(mQueue, new LruBitmapCache());

        ((NetworkImageView) view.findViewById(R.id.THUMB_IMAGE)).setImageUrl("http://culture.suwon.go.kr/common-upload" + mFestival.THUMB_IMAGE, mImageLoader);
        ((TextView)view.findViewById(R.id.CULTURE_NM)).setText(mFestival.CULTURE_NM);
        ((TextView)view.findViewById(R.id.START_DT)).setText        (mFestival.START_DT);
        ((TextView)view.findViewById(R.id.END_DT)).setText          (mFestival.END_DT);
        ((TextView)view.findViewById(R.id.START_TIME)).setText      (mFestival.START_TIME);
        ((TextView)view.findViewById(R.id.END_TIME)).setText        (mFestival.END_TIME);
        ((TextView)view.findViewById(R.id.CTR_LOCATION)).setText    (mFestival.CTR_LOCATION);
        ((TextView)view.findViewById(R.id.TEL_NO)).setText          (mFestival.TEL_NO);
        ((TextView)view.findViewById(R.id.HOMEPAGE_URL)).setText    (mFestival.HOMEPAGE_URL);
        ((TextView)view.findViewById(R.id.TICKET_PRICE)).setText    (mFestival.TICKET_PRICE);
        ((TextView)view.findViewById(R.id.VIEW_AGE)).setText        (mFestival.VIEW_AGE);

        return view;
    }

}
