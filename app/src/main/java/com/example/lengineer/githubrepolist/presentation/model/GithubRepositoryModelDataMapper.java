package com.example.lengineer.githubrepolist.presentation.model;

import com.example.lengineer.githubrepolist.domain.model.GithubRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Mapper class used to transform {@link GithubRepository} (in the domain layer) to {@link GithubRepositoryModel} in the
 * presentation layer.
 */
public class GithubRepositoryModelDataMapper implements Function<GithubRepository, GithubRepositoryModel> {

    /**
     * Transform a {@link GithubRepository} into an {@link GithubRepositoryModel}.
     *
     * @param githubRepository Object to be transformed.
     * @return {@link GithubRepositoryModel}.
     */
    public GithubRepositoryModel apply (GithubRepository githubRepository)throws Exception{
        GithubRepositoryModel model = new GithubRepositoryModel();
        model.setId(githubRepository.getId());
        model.setName(githubRepository.getName());
        model.setOwnerName(githubRepository.getOwnerName());
        model.setStargazersCount(githubRepository.getStargazersCount());
        model.setUrl(githubRepository.getUrl());
        return model;
    }

}
