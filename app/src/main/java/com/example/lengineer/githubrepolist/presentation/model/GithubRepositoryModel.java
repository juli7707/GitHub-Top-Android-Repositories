package com.example.lengineer.githubrepolist.presentation.model;

/**
 * Class that represents a repository in the presentation layer.
 */
public class GithubRepositoryModel {

    private long id;
    private String name;
    private String ownerName;
    private int stargazersCount;
    private String url;

    public GithubRepositoryModel(long id, String name, String ownerName, int stargazersCount, String url) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.stargazersCount = stargazersCount;
        this.url = url;
    }

    public GithubRepositoryModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
