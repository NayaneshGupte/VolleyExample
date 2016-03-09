package com.twittercrashlytics.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.twittercrashlytics.R;
import com.twittercrashlytics.fragment.GitIssuesListFragment;

/**
 * Activity holding issues list fragment.
 * <p/>
 * Launcher Activity.
 */
public class GitIssuesListActivity extends AppCompatActivity {


    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_git_issues_list);

        setupToolbar();

        addIssuesListFragment();


    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    /**
     * Add fragment to display list of issues
     */
    private void addIssuesListFragment() {

        GitIssuesListFragment gitIssuesListFragment = new GitIssuesListFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, gitIssuesListFragment).commit();

    }


}
