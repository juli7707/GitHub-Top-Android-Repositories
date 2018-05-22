package com.example.lengineer.githubrepolist.domain.interactor;

import com.example.lengineer.githubrepolist.data.repository.IGithubRepositoryListRepository;
import com.example.lengineer.githubrepolist.domain.model.GithubRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * This class is an implementation of {@link IGithubRepositoryListInteractor} that represents a use case for
 * retrieving a list of {@link GithubRepository}.
 */
public class GithubRepositoryListInteractor implements IGithubRepositoryListInteractor {
    private IGithubRepositoryListRepository mRepository;

    public GithubRepositoryListInteractor(IGithubRepositoryListRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public Observable<GithubRepository> loadGithubRepositories(int page) {
        return mRepository.getAndroidTopRepositories(page);
    }
}
