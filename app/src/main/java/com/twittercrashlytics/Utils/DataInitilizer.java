package com.twittercrashlytics.Utils;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.twittercrashlytics.TwitterCrashlyticsApplication;
import com.twittercrashlytics.network.GsonRequest;
import com.twittercrashlytics.network.model.Comment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nayanesh Gupte
 */
public class DataInitilizer implements Response.Listener<Comment[]>, Response.ErrorListener {


    private final static String TAG = DataInitilizer.class.getSimpleName();


    private Context context;

    private OnDataReeceivedListener onDataReeceivedListener;


    public DataInitilizer(Context context) {

        this.context = context;
    }

    public void setOnDataReeceivedListener(OnDataReeceivedListener onDataReeceivedListener) {
        this.onDataReeceivedListener = onDataReeceivedListener;
    }


    public void getCommentsList(String commentsUrl) {

        if (AppUtil.isNetworkAvailable(context)) {
            GsonRequest<Comment[]> myReq = new GsonRequest<Comment[]>(commentsUrl,
                    Comment[].class,
                    null,
                    this,
                    this);

            TwitterCrashlyticsApplication.getInstance().addToRequestQueue(myReq, TAG);
        } else {


        }
    }


    @Override
    public void onResponse(Comment[] response) {

        List<Comment> listIssues = Arrays.asList(response);

        Collections.sort(listIssues, new DateComparator());

        onDataReeceivedListener.onDataReceived(listIssues);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        onDataReeceivedListener.onError(error);

    }
}
