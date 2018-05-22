package com.example.lengineer.githubrepolist.domain.interactor;

import com.example.lengineer.githubrepolist.domain.model.GithubRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IGithubRepositoryListInteractor {
    Observable<GithubRepository> loadGithubRepositories(int page);
}
