package com.example.lengineer.githubrepolist.presentation.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lengineer.githubrepolist.R;
import com.example.lengineer.githubrepolist.presentation.model.GithubRepositoryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adaptar that manages a collection of {@link GithubRepositoryModel}.
 */
public class GithubRepositoriesAdapter extends RecyclerView.Adapter<GithubRepositoriesAdapter.GithubRepositoryViewHolder> {
    private List<GithubRepositoryModel> mRepositories;
    private OnItemClickListener mListener;

    public GithubRepositoriesAdapter() {
        mRepositories = new ArrayList<>();
    }

    @NonNull
    @Override
    public GithubRepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_repository_item, parent, false);
        return new GithubRepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubRepositoryViewHolder holder, int position) {
        final GithubRepositoryModel model = mRepositories.get(position);
        holder.bindRepository(model);
        holder.itemView.setOnClickListener(v -> {
            if (mListener != null){
                mListener.onRepositoryItemClicked(model);
            }
        });
    }

    public void updateDataSet(List<GithubRepositoryModel> list){
        if (list != null){
            mRepositories.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void refreshDataSet(List<GithubRepositoryModel> list){
        if (list != null){
            mRepositories.clear();
            mRepositories.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mRepositories != null ? mRepositories.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface OnItemClickListener {
        void onRepositoryItemClicked(GithubRepositoryModel model);
    }

    class GithubRepositoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repository_name)
        TextView mRepositoryNameTxt;
        @BindView(R.id.repository_owner)
        TextView mRepositoryOwnerTxt;
        @BindView(R.id.repository_stargazers_count)
        TextView mStargazersCount;

        GithubRepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindRepository(GithubRepositoryModel model){
            mRepositoryNameTxt.setText(model.getName());
            mRepositoryOwnerTxt.setText(model.getOwnerName());
            mStargazersCount.setText(String.valueOf(model.getStargazersCount()));
        }
    }
}
