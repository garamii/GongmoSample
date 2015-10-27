package com.example.gongmosample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gongmosample.R;
import com.example.gongmosample.models.Festival;

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
        ((TextView)view.findViewById(R.id.CULTURE_NM)).setText(mFestival.CULTURE_NM);
        return view;
    }

}
