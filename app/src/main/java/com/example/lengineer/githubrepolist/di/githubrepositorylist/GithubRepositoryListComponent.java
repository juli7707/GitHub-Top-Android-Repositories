package com.example.lengineer.githubrepolist.di.githubrepositorylist;

import com.example.lengineer.githubrepolist.presentation.presenter.GithubRepositoryListPresenter;

import dagger.Component;
import dagger.Subcomponent;

@GithubRepositoryListScope
@Subcomponent(modules = GithubRepositoryListModule.class)
public interface GithubRepositoryListComponent {
    void inject(GithubRepositoryListPresenter presenter);
}
