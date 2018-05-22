package com.example.lengineer.githubrepolist.di.application;

import com.example.lengineer.githubrepolist.di.githubrepositorylist.GithubRepositoryListComponent;
import com.example.lengineer.githubrepolist.di.githubrepositorylist.GithubRepositoryListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    GithubRepositoryListComponent plus(GithubRepositoryListModule module);
}
