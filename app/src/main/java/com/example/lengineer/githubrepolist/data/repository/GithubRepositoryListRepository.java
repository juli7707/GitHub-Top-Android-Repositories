package com.example.lengineer.githubrepolist.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.lengineer.githubrepolist.data.entity.GithubRepositoryEntity;
import com.example.lengineer.githubrepolist.data.entity.GithubRepositoryResponse;
import com.example.lengineer.githubrepolist.data.entity.mapper.GithubRepositoryEntityDataMapper;
import com.example.lengineer.githubrepolist.data.exeption.NetworkConnectionException;
import com.example.lengineer.githubrepolist.data.network.GithubApi;
import com.example.lengineer.githubrepolist.domain.model.GithubRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class GithubRepositoryListRepository implements IGithubRepositoryListRepository {
    private GithubApi mApi;
    private Context mContext;

    public GithubRepositoryListRepository(GithubApi mApi, Context mContext) {
        this.mApi = mApi;
        this.mContext = mContext;
    }

    @Override
    public Observable<GithubRepository> getAndroidTopRepositories(int pageNumber) {
        return isThereInternetConnection() ?
                mApi.getAndroidTopRepositories(pageNumber)
                        .map(GithubRepositoryResponse::getItems)
                        .flatMapObservable(Observable::fromIterable)
                        .map(new GithubRepositoryEntityDataMapper()) :
                Observable.error(new NetworkConnectionException());
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
