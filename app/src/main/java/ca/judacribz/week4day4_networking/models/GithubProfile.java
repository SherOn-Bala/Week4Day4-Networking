package ca.judacribz.week4day4_networking.models;

import java.util.ArrayList;
import java.util.List;

public class GithubProfile {
    String
            login,
            avatar_url;
    int public_repos;

    ArrayList<GithubRepo> repos;

    public GithubProfile(String login, String avatar_url, int public_repos) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.public_repos = public_repos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public ArrayList<GithubRepo> getRepos() {
        return repos;
    }

    public void setRepos(ArrayList<GithubRepo> repos) {
        this.repos = repos;
    }
}
