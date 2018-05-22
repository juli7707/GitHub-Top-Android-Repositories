package com.example.lengineer.githubrepolist.data.entity.mapper;

import com.example.lengineer.githubrepolist.data.entity.GithubRepositoryEntity;
import com.example.lengineer.githubrepolist.domain.model.GithubRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Mapper class used to transform {@link GithubRepositoryEntity} (in the data layer) to {@link GithubRepository} in the
 * domain layer.
 */
public class GithubRepositoryEntityDataMapper implements Function<GithubRepositoryEntity, GithubRepository> {

    /**
     * Transform a {@link GithubRepository} into an {@link GithubRepository}.
     *
     * @param entity Object to be transformed.
     * @return {@link GithubRepository}.
     */
    @Override
    public GithubRepository apply(GithubRepositoryEntity entity) throws Exception {
        GithubRepository repository = new GithubRepository();
        repository.setId(entity.getId());
        repository.setName(entity.getName());
        repository.setOwnerName(entity.getOwner().getLogin());
        repository.setStargazersCount(entity.getStargazersCount());
        repository.setUrl(entity.getUrl());
        return repository;
    }



}
