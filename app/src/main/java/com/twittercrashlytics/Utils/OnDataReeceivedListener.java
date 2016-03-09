package com.twittercrashlytics.Utils;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Nayanesh Gupte on 06/03/16.
 */
public interface OnDataReeceivedListener<T> {

    void onDataReceived(List<T> object);

    void onError(VolleyError volleyError);
}
