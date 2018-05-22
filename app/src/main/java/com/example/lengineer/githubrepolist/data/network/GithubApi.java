package com.example.lengineer.githubrepolist.data.network;

import com.example.lengineer.githubrepolist.data.entity.GithubRepositoryResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * RestApi for retrieving data from the network.
 */
public interface GithubApi {

    /**
     * Retrieves an {@link Single} which will emit a {@link GithubRepositoryResponse}.
     *
     * @param pageNumber The number of page to get.
     */

    @GET("search/repositories?q=topic:android&sort=stars&order=desc")
    Single<GithubRepositoryResponse> getAndroidTopRepositories(@Query("page") int pageNumber);
}
