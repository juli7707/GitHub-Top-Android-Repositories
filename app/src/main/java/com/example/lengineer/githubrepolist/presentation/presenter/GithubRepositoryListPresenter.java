package com.example.lengineer.githubrepolist.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.lengineer.githubrepolist.App;
import com.example.lengineer.githubrepolist.R;
import com.example.lengineer.githubrepolist.data.exeption.NetworkConnectionException;
import com.example.lengineer.githubrepolist.di.githubrepositorylist.GithubRepositoryListModule;
import com.example.lengineer.githubrepolist.domain.interactor.IGithubRepositoryListInteractor;
import com.example.lengineer.githubrepolist.domain.model.GithubRepository;
import com.example.lengineer.githubrepolist.presentation.model.GithubRepositoryModel;
import com.example.lengineer.githubrepolist.presentation.model.GithubRepositoryModelDataMapper;
import com.example.lengineer.githubrepolist.presentation.view.fragment.GithubRepositoryListView;

import java.net.SocketTimeoutException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter controls communication between views and models of the presentation
 * layer.
 */
@InjectViewState
public class GithubRepositoryListPresenter extends MvpPresenter<GithubRepositoryListView> implements IGithubRepositoryListPresenter {
    @Inject
    IGithubRepositoryListInteractor mInteractor;

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private int mPageCount = 1;
    private boolean isLoading = false;

    public GithubRepositoryListPresenter() {
        App.getApp().plusGithubRepositoryListModule(new GithubRepositoryListModule()).inject(this);
    }

    @Override
    public void init() {
        loadRepositories();
    }

    private void loadRepositories() {
        mDisposable.add(mInteractor.loadGithubRepositories(mPageCount)
                .compose(this::applySchedulers)
                .toList()
                .doOnSubscribe(disposable -> {
                    getViewState().showProgress();
                    isLoading = true;
                })
                .doAfterTerminate(() -> {
                    getViewState().hideProgress();
                    isLoading = false;
                })
                .subscribe(this::handleLoadingSuccess, this::handleLoadingError));
    }

    private void handleLoadingSuccess(List<GithubRepositoryModel> models) {
        getViewState().showItems(models);
        mPageCount++;
    }

    private void handleLoadingError(Throwable throwable) {
        getViewState().showRetryView(getErrorMessage(throwable));
    }

    @Override
    public void onRefreshSwiped() {
        final int firstPage = 1;
        mDisposable.add(mInteractor.loadGithubRepositories(firstPage)
                .compose(this::applySchedulers)
                .toList()
                .doOnSubscribe(disposable -> isLoading = true)
                .doAfterTerminate(() -> {
                    getViewState().hideRefreshing();
                    isLoading = false;
                })
                .subscribe(this::handleRefreshSuccess, this::handleRefreshError));
    }

    private ObservableSource<GithubRepositoryModel> applySchedulers(Observable<GithubRepository> githubRepositoryObservable) {
        return githubRepositoryObservable.map(new GithubRepositoryModelDataMapper())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void handleRefreshError(Throwable throwable) {
        getViewState().showError(getErrorMessage(throwable));
    }

    private void handleRefreshSuccess(List<GithubRepositoryModel> models) {
        mPageCount = 1;
        getViewState().refreshItems(models);
    }

    @Override
    public void onScrolled(int lastVisibleItemPosition, int totalItemCount) {
        if (!isLoading && lastVisibleItemPosition == totalItemCount - 1) {
            loadRepositories();
        }
    }

    @Override
    public void onRepositoryClicked(GithubRepositoryModel model) {
        getViewState().showRepositoryPage(model);
    }

    @Override
    public void onRetryButtonClicked() {
        getViewState().hideRetryView();
        loadRepositories();
    }

    private int getErrorMessage(Throwable throwable){
        int message = R.string.exception_message_generic;

        if (throwable instanceof NetworkConnectionException) {
            message = R.string.exception_message_no_connection;
        } else if (throwable instanceof SocketTimeoutException) {
            message = R.string.exception_message_socket_timeout;
        }

        return message;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}
