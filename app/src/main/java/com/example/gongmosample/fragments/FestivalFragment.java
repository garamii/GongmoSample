package com.example.gongmosample.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.gongmosample.R;
import com.example.gongmosample.activities.DetailActivity;
import com.example.gongmosample.models.Festival;
import com.example.gongmosample.views.adapters.FestivalAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by junsuk on 2015. 10. 15..
 */
public class FestivalFragment extends Fragment implements View.OnKeyListener, AdapterView.OnItemClickListener {

    private static final String TAG = FestivalFragment.class.getSimpleName();

    // 날씨 예보 제공 URL
    private static final String URL_FORECAST = "http://27.101.101.31/openapi-data/service/FestivalEvents/festivalEventsList?pageNo=1&numOfRows=1000&ServiceKey=czL0tLAf%2Fjn3knJuCqUKRaBjIX%2BOzhCiQVh5O%2B4QEgoMPguQitQUM%2B7wonJKyxbY9he2JzA%2BW7IwFd6VBvXUIQ%3D%3D";

    private ListView mWeatherListView;
    private FestivalAdapter mAdapter;

    private ProgressBar mProgressBar;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    // 클라이언트 오브젝트
    private OkHttpClient client = new OkHttpClient();
    private ArrayList<Festival> mFestivalList;

    /**
     * url 로 부터 스트림을 읽어 String 으로 반환한다
     * @param url
     * @return
     * @throws IOException
     */
    private String getResponse(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        mWeatherListView = (ListView) view.findViewById(R.id.lv_weather);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO: Pull to refresh 동작시 처리 구현

                // refresh 애니메이션 종료
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mWeatherListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new WeatherInfoLoadTask().execute();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 아이템 클릭
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putParcelableArrayListExtra("list", mFestivalList);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    class WeatherInfoLoadTask extends AsyncTask<Void, Void, List> {
        @Override
        protected void onPreExecute() {

            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List doInBackground(Void... params) { // 첫번째 인자
            mFestivalList = null;


            try {
                // HTTP 에서 내용을 String 으로 받아 온다
                String jsonString = getResponse(URL_FORECAST);


               // Log.d(TAG, jsonObject1.toString());

                JSONObject jsonObject = XML.toJSONObject(jsonString);
//                Log.d(TAG, jsonObject.toString());
                JSONObject items = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items");

                Log.d(TAG, items.toString());
                JSONArray jsonArray = items.getJSONArray("item");


                ObjectMapper objectMapper = new ObjectMapper();

                mFestivalList = objectMapper.readValue(jsonArray.toString(),
                        objectMapper.getTypeFactory().constructCollectionType(
                                List.class, Festival.class
                        ));

                // 정렬
                Collections.sort(mFestivalList, new Comparator<Festival>() {
                    @Override
                    public int compare(Festival lhs, Festival rhs) {
                        if (lhs.START_DT.compareTo(rhs.START_DT) > 0) {
                            return 1;
                        } else if (lhs.START_DT.compareTo(rhs.START_DT) < 0) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String today = format.format(System.currentTimeMillis());

                Log.d(TAG, today);

                ArrayList<Festival> list = new ArrayList<>();

                for (int i = 0; i < mFestivalList.size(); i++) {
                    Festival festival = mFestivalList.get(i);
                    if (festival.END_DT.compareTo(today) > 0) {
                        list.add(festival);
                    }
                }
                mFestivalList = list;

                Log.d(TAG, mFestivalList.toString());



//                // 받아온 JSON String 을 JSON Object로 변환한다
//                JSONObject jsonObject = new JSONObject(jsonString);
//                JSONArray jsonArray = jsonObject.getJSONArray("list");
//
//                // 날씨 정보 저장할 리스트
//                festivalList = new ArrayList<>();
//
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject object = jsonArray.getJSONObject(i);
//
//                    String time = object.getString("dt_txt");
//                    time = time.split(" ")[1].substring(0, 5);
//                    String temp = object.getJSONObject("main").getString("temp");
//                    String description = object.getJSONArray("weather")
//                            .getJSONObject(0).getString("description");
//
//                    festivalList.add(new Festival(time, temp, description));
//                }

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            return mFestivalList;
        }

        // publishUpdate로만 호출
        @Override
        protected void onProgressUpdate(Void... values) { // 두번째 인자
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List list) { // 세번째 인자
            mAdapter = new FestivalAdapter(getActivity(), list);
            mWeatherListView.setAdapter(mAdapter);
            mProgressBar.setVisibility(View.GONE);
        }
    }

}
