package com.example.lengineer.githubrepolist;

import android.app.Application;

import com.example.lengineer.githubrepolist.di.application.AppComponent;
import com.example.lengineer.githubrepolist.di.application.AppModule;
import com.example.lengineer.githubrepolist.di.application.DaggerAppComponent;
import com.example.lengineer.githubrepolist.di.githubrepositorylist.GithubRepositoryListComponent;
import com.example.lengineer.githubrepolist.di.githubrepositorylist.GithubRepositoryListModule;

public class App extends Application {
    public static App mApp;
    private AppComponent mAppComponent;
    private GithubRepositoryListComponent mGithubRepositoryListComponent;

    public App() {
        mApp = this;
    }

    public static App getApp() {
        return mApp;
    }

    @Override
    public void onCreate() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public GithubRepositoryListComponent plusGithubRepositoryListModule(GithubRepositoryListModule module) {
        if (mGithubRepositoryListComponent == null) {
            mGithubRepositoryListComponent = getAppComponent().plus(module);
        }
        return mGithubRepositoryListComponent;
    }

    public void clearGithubRepositoryListComponent() {
        mGithubRepositoryListComponent = null;
    }
}
