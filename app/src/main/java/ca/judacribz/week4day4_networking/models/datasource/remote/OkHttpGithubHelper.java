package ca.judacribz.week4day4_networking.models.datasource.remote;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ca.judacribz.week4day4_networking.models.GithubProfile;
import ca.judacribz.week4day4_networking.models.GithubRepo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpGithubHelper {
    private static final String
            GITHUB_USER_URL = "https://api.github.com/users/judacribz",
            GITHUB_USER_REPOS_URL = "https://api.github.com/users/judacribz/repos";
    private static final Type TYPE_GITHUB_REPO_LIST = new TypeToken<ArrayList<GithubRepo>>() {
    }.getType();

    private static Request
            profileRequest,
            repoRequest;
    private static Response
            profileResponse,
            reposResponse;
    private static GithubProfile githubProfile;
    private static List<GithubRepo> githubRepos;

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    static GithubProfile getSynchronousOkHttpResponse() throws IOException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                profileRequest = new Request
                        .Builder()
                        .url(GITHUB_USER_URL)
                        .build();

                try {
                    profileResponse = getClient().newCall(profileRequest).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                repoRequest = new Request
                        .Builder()
                        .url(GITHUB_USER_REPOS_URL)
                        .build();

                try {
                    reposResponse = getClient().newCall(repoRequest).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();

            githubProfile = new Gson().fromJson(profileResponse.body().string(), GithubProfile.class);
            githubRepos = new Gson().fromJson(reposResponse.body().string(), TYPE_GITHUB_REPO_LIST);

            githubProfile.setRepos(githubRepos);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return githubProfile;
    }
}
