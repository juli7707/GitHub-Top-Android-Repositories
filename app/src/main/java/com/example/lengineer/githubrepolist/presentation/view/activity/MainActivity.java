package com.example.lengineer.githubrepolist.presentation.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lengineer.githubrepolist.R;
import com.example.lengineer.githubrepolist.presentation.model.GithubRepositoryModel;
import com.example.lengineer.githubrepolist.presentation.view.fragment.GithubRepositoryListFragment;

/**
 * Main application screen. This is the app entry point.
 * This activity is responsible for displaying the list of github repository
 */
public class MainActivity extends BaseActivity implements GithubRepositoryListFragment.GithubRepositoryListListener {

    @Override
    protected Fragment createFragment() {
        return new GithubRepositoryListFragment();
    }

    @Override
    public void onRepositoryClicked(GithubRepositoryModel model) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getUrl()));
        startActivity(intent);
    }
}
