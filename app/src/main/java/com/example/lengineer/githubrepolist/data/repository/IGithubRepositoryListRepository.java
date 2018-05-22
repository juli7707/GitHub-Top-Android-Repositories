package com.example.lengineer.githubrepolist.data.repository;

import com.example.lengineer.githubrepolist.domain.model.GithubRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IGithubRepositoryListRepository {
    Observable<GithubRepository> getAndroidTopRepositories(int pageNumber);
}
