package com.twittercrashlytics.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.twittercrashlytics.R;
import com.twittercrashlytics.TwitterCrashlyticsApplication;
import com.twittercrashlytics.Utils.AppUtil;
import com.twittercrashlytics.Utils.DateComparator;
import com.twittercrashlytics.Utils.IAppConstants;
import com.twittercrashlytics.Utils.OnItemClickListener;
import com.twittercrashlytics.adapter.IssuesListAdapter;
import com.twittercrashlytics.network.GsonRequest;
import com.twittercrashlytics.network.model.Issue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Fragment holding list of issues.
 */
public class GitIssuesListFragment extends Fragment implements IAppConstants, OnItemClickListener {


    private static final String TAG = GitIssuesListFragment.class.getSimpleName();

    //-------------------------------------------------------
    //Views
    //-------------------------------------------------------
    private RecyclerView recyListComments;
    private ProgressBar progressBar;

    //-------------------------------------------------------
    //Variables
    //-------------------------------------------------------
    private IssuesListAdapter issuesListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_git_issues_list, container, false);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyListComments = (RecyclerView) view.findViewById(R.id.recyListComments);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        initRecyclerView();
    }

    /**
     * Initialize recycler view. Initialization in onCreateView crashes in some devices.
     */
    private void initRecyclerView() {

        recyListComments.setLayoutManager(new LinearLayoutManager(getActivity()));
        issuesListAdapter = new IssuesListAdapter(getActivity());
        recyListComments.setAdapter(issuesListAdapter);
        issuesListAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getIssuesList();
    }

    private void getIssuesList() {

        if (AppUtil.isNetworkAvailable(getActivity())) {

            GsonRequest<Issue[]> myReq = new GsonRequest<Issue[]>(BASE_URL,
                    Issue[].class,
                    null,
                    createMyReqSuccessListener(),
                    createMyReqErrorListener());

            TwitterCrashlyticsApplication.getInstance().addToRequestQueue(myReq, TAG);
        } else {

            progressBar.setVisibility(View.GONE);

            Toast.makeText(getActivity(), getString(R.string.no_network), Toast.LENGTH_SHORT).show();

        }


    }

    private Response.Listener<Issue[]> createMyReqSuccessListener() {
        return new Response.Listener<Issue[]>() {
            @Override
            public void onResponse(Issue[] response) {

                List<Issue> listIssues = Arrays.asList(response);

                Collections.sort(listIssues, new DateComparator());

                issuesListAdapter.add(listIssues);


                progressBar.setVisibility(View.GONE);

            }

        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);

                Log.e(TAG, "onErrorResponse: " + error.toString());

            }
        };
    }

    @Override
    public void onItemClick(Object object, int position) {

        Issue issue = (Issue) object;

        GitCommentsListFragment gitCommentsListFragment = new GitCommentsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_COMMENTS_URL, issue.getCommentsUrl());
        bundle.putString(KEY_ISSUE_ID, issue.getId());
        gitCommentsListFragment.setArguments(bundle);
        gitCommentsListFragment.show(getActivity().getSupportFragmentManager(), TAG);

    }

    @Override
    public void onDetach() {
        super.onDetach();

        TwitterCrashlyticsApplication.getInstance().cancelPendingRequests(TAG);
    }


}
