package com.example.lengineer.githubrepolist.di.githubrepositorylist;

import android.content.Context;

import com.example.lengineer.githubrepolist.data.network.GithubApi;
import com.example.lengineer.githubrepolist.data.repository.GithubRepositoryListRepository;
import com.example.lengineer.githubrepolist.data.repository.IGithubRepositoryListRepository;
import com.example.lengineer.githubrepolist.domain.interactor.GithubRepositoryListInteractor;
import com.example.lengineer.githubrepolist.domain.interactor.IGithubRepositoryListInteractor;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
@Module
public class GithubRepositoryListModule {
    @Provides
    @GithubRepositoryListScope
    IGithubRepositoryListRepository provideRepository(Retrofit retrofit, Context context) {
        return new GithubRepositoryListRepository(retrofit.create(GithubApi.class), context);
    }

    @Provides
    @GithubRepositoryListScope
    IGithubRepositoryListInteractor provideInteractor(IGithubRepositoryListRepository repository) {
        return new GithubRepositoryListInteractor(repository);
    }
}
