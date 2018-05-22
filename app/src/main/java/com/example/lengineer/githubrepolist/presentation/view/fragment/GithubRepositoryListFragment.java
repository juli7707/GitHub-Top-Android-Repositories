package com.example.lengineer.githubrepolist.presentation.view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.lengineer.githubrepolist.R;
import com.example.lengineer.githubrepolist.presentation.model.GithubRepositoryModel;
import com.example.lengineer.githubrepolist.presentation.presenter.GithubRepositoryListPresenter;
import com.example.lengineer.githubrepolist.presentation.view.adapter.GithubRepositoriesAdapter;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Fragment that shows a list of github repository.
 */
public class GithubRepositoryListFragment extends MvpAppCompatFragment implements GithubRepositoryListView {
    @BindView(R.id.repository_recycler_view)
    RecyclerView mRepositoriesRecyclerView;
    @BindView(R.id.repository_swipe_refresh)
    SwipeRefreshLayout mRepositoriesSwipeRefresh;
    @BindView(R.id.progress_bar)
    ProgressBar mRepositoriesProgressBar;
    @BindView(R.id.retry_layout)
    LinearLayout mRepositoriesRetryLayout;
    @BindView(R.id.retry_loading_button)
    Button mRepositoriesRetryButton;
    @BindView(R.id.retry_msg_txt)
    TextView mRepositoriesErrorMsgTxt;

    @InjectPresenter
    GithubRepositoryListPresenter mPresenter;

    private Unbinder mUnbinder;
    private GithubRepositoriesAdapter mAdapter;
    private GithubRepositoryListListener mListListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof GithubRepositoryListListener) {
            mListListener = (GithubRepositoryListListener) activity;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_github_repository_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUnbinder = ButterKnife.bind(this, view);

        initRecyclerView();
        initSwipeToRefresh();

        if (savedInstanceState == null) {
            mPresenter.init();
        }
    }


    private void initSwipeToRefresh() {
        mRepositoriesSwipeRefresh.setOnRefreshListener(() -> mPresenter.onRefreshSwiped());
        mRepositoriesSwipeRefresh.setColorSchemeResources(R.color.colorAccent);
    }

    private void initRecyclerView() {
        mAdapter = new GithubRepositoriesAdapter();
        mAdapter.setOnItemClickListener(model -> mPresenter.onRepositoryClicked(model));
        mRepositoriesRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL));
        mRepositoriesRecyclerView.setAdapter(mAdapter);
        mRepositoriesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                mPresenter.onScrolled(layoutManager.findLastVisibleItemPosition(), layoutManager.getItemCount());
            }
        });
    }

    @OnClick(R.id.retry_loading_button)
    void onRetryClicked() {
        mPresenter.onRetryButtonClicked();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();

    }

    @Override
    public void showProgress() {
        mRepositoriesProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mRepositoriesProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideRefreshing() {
        mRepositoriesSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showRetryView(int errorMsg) {
        mRepositoriesErrorMsgTxt.setText(errorMsg);
        mRepositoriesRetryLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetryView() {
        mRepositoriesRetryLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(int errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRepositoryPage(GithubRepositoryModel model) {
        if (mListListener != null){
            mListListener.onRepositoryClicked(model);
        }
    }

    @Override
    public void refreshItems(List<GithubRepositoryModel> models) {
        mAdapter.refreshDataSet(models);
    }

    @Override
    public void showItems(List<GithubRepositoryModel> models) {
        mAdapter.updateDataSet(models);
    }

    public interface GithubRepositoryListListener {
        void onRepositoryClicked(GithubRepositoryModel githubRepositoryModel);
    }

}
