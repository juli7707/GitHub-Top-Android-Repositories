package com.example.lengineer.githubrepolist.presentation.view.fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.lengineer.githubrepolist.presentation.model.GithubRepositoryModel;

import java.util.List;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link GithubRepositoryModel}.
 */
@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface GithubRepositoryListView extends MvpView {
    void showProgress();

    void hideProgress();

    void hideRefreshing();

    void showRetryView(int errorMsg);

    void hideRetryView();

    @StateStrategyType(value = SkipStrategy.class)
    void showError(int errorMsg);

    void showRepositoryPage(GithubRepositoryModel model);

    @StateStrategyType(value = SingleStateStrategy.class)
    void refreshItems(List<GithubRepositoryModel> models);

    @StateStrategyType(value = AddToEndStrategy.class)
    void showItems(List<GithubRepositoryModel> models);

}
