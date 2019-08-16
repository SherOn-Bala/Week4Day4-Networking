package ca.judacribz.week4day4_networking.models.datasource.events;

import ca.judacribz.week4day4_networking.models.GithubProfile;

public class OkHttpGithubResponseEvent {
    private GithubProfile githubProfile;

    public OkHttpGithubResponseEvent(GithubProfile GithubProfile) {
        this.githubProfile = GithubProfile;
    }

    public GithubProfile getGithubProfile() {
        return githubProfile;
    }

    public void setGithubProfile(GithubProfile githubProfile) {
        this.githubProfile = githubProfile;
    }
}
