package ca.judacribz.week4day4_networking.models.datasource.remote;

import android.os.AsyncTask;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import ca.judacribz.week4day4_networking.models.GithubProfile;
import ca.judacribz.week4day4_networking.models.datasource.events.OkHttpGithubResponseEvent;

public class OkHttpGithubTask extends AsyncTask<Void, String, GithubProfile> {

    @Override
    protected GithubProfile doInBackground(Void... voids) {
        GithubProfile profile = null;

        try {
            profile = OkHttpGithubHelper.getSynchronousOkHttpResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profile;
    }

    @Override
    protected void onPostExecute(@Nullable GithubProfile githubProfile) {
        if (githubProfile != null) {
            EventBus.getDefault().post(new OkHttpGithubResponseEvent(githubProfile));
        }
    }
}
