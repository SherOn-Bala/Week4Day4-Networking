package ca.judacribz.week4day4_networking.models.datasource.remote;

import android.os.AsyncTask;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import ca.judacribz.week4day4_networking.models.GithubProfile;
import ca.judacribz.week4day4_networking.models.datasource.events.OkHttpGithubResponseEvent;

public class OkHttpGithubTask extends AsyncTask<Void, String, GithubProfile> {
    public static final String TAG = "TAG_ASYNC_TASK";

    @Override
    protected GithubProfile doInBackground(Void... voids) {
        try {
            return OkHttpGithubHelper.getSynchronousOkHttpResponse();
        } catch (IOException e) {
            publishProgress(e.toString());
            return null;
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + values[0]);
    }

    @Override
    protected void onPostExecute(GithubProfile githubProfile) {
        super.onPostExecute(githubProfile);
        EventBus.getDefault().post(new OkHttpGithubResponseEvent(githubProfile));
    }
}
