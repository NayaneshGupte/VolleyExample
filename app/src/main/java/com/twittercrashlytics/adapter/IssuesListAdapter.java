package com.twittercrashlytics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.twittercrashlytics.R;
import com.twittercrashlytics.TwitterCrashlyticsApplication;
import com.twittercrashlytics.Utils.OnItemClickListener;
import com.twittercrashlytics.network.model.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class IssuesListAdapter extends RecyclerView.Adapter<IssuesListAdapter.IssuesViewHolder> {
    List<Issue> data = new ArrayList<>();
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    private ImageLoader imageLoader;

    public IssuesListAdapter(Context context) {
        inflater = LayoutInflater.from(context);

        imageLoader = TwitterCrashlyticsApplication.getInstance().getImageLoader();
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void add(List<Issue> data) {

        this.data.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public IssuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_issues, parent, false);
        return new IssuesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final IssuesViewHolder holder, final int position) {

        final Issue issue = data.get(position);

        //--- setImage

        holder.imgUser.setDefaultImageResId(R.drawable.ic_launcher);
        holder.imgUser.setErrorImageResId(R.drawable.ic_launcher);
        holder.imgUser.setImageUrl(issue.getUser().getProfilepicUrl(), imageLoader);

        //--- set title
        holder.txtvTitle.setText(issue.getTitle());


        //--- set Issue body
        holder.txtvIssue.setText(issue.getBody());


        //-----
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.onItemClick(issue, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class IssuesViewHolder extends RecyclerView.ViewHolder {

        final TextView txtvTitle;

        final TextView txtvIssue;

        final NetworkImageView imgUser;

        final View view;

        public IssuesViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            txtvTitle = (TextView) itemView.findViewById(R.id.txtvTitle);

            txtvIssue = (TextView) itemView.findViewById(R.id.txtvIssue);

            imgUser = (NetworkImageView) itemView.findViewById(R.id.imgUser);
        }
    }
}