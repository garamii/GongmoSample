package com.example.gongmosample.volley;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by junsuk on 2015. 10. 20..
 *
 * 프로그레스바를 지원하는 Volley 용 ImageLoader
 */
public class ImageLoaderPlus extends ImageLoader {
    /**
     * Constructs a new ImageLoader.
     *
     * @param queue      The RequestQueue to use for making image requests.
     * @param imageCache The cache to use as an L1 cache.
     */
    public ImageLoaderPlus(RequestQueue queue, ImageCache imageCache) {
        super(queue, imageCache);
    }

    public static ImageListener getImageListener(final ImageView view,
                                                 final ProgressBar progressBar,
                                                 final int errorImageResId) {
        return new ImageListener() {
            @Override
            public void onResponse(ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    view.setImageBitmap(response.getBitmap());
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if (errorImageResId != 0) {
                    view.setImageResource(errorImageResId);
                } else {
                    view.setImageResource(android.R.drawable.ic_menu_report_image);
                }
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        };
    }
}
