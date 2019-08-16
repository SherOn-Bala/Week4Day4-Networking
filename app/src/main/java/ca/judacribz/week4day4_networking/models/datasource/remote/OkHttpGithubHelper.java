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
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.http.Body;

public class OkHttpGithubHelper {
    private static final String
            GITHUB_USER_URL = "https://api.github.com/users/judacribz",
            GITHUB_USER_REPOS_URL = "https://api.github.com/users/judacribz/repos";

    // Used to convert JSON string of repos to list of GithubRepo Objects
    private static final Type TYPE_GITHUB_REPO_LIST = new TypeToken<ArrayList<GithubRepo>>() {
    }.getType();

    private static Response reposResponse;

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    static GithubProfile getSynchronousOkHttpResponse() throws IOException {
        GithubProfile githubProfile = null;
        ResponseBody proBody, repoBody;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Request repoRequest = new Request
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
        thread.start();

        Response profileResponse = getClient().newCall(
                new Request
                        .Builder()
                        .url(GITHUB_USER_URL)
                        .build()
        ).execute();

        try {
            thread.join();

            // Add github profile json values to github profile object
            if ((proBody = profileResponse.body()) != null) {
                githubProfile = new Gson().fromJson(proBody.string(), GithubProfile.class);

                /* Add github users repo json values to github repos object list and set it within
                 * github profile object */
                if ((repoBody = reposResponse.body()) != null) {
                    githubProfile.setRepos(new Gson().<List<GithubRepo>>fromJson(
                            repoBody.string(),
                            TYPE_GITHUB_REPO_LIST
                    ));
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return githubProfile;
    }
}
