package com.example.lengineer.githubrepolist.presentation.presenter;

import com.example.lengineer.githubrepolist.presentation.model.GithubRepositoryModel;

public interface IGithubRepositoryListPresenter {
    void init();
    void onRefreshSwiped();
    void onScrolled(int lastVisibleItemPosition, int totalItemCount);
    void onRepositoryClicked(GithubRepositoryModel model);
    void onRetryButtonClicked();

}
