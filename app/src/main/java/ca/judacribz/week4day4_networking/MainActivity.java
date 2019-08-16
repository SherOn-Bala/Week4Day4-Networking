package ca.judacribz.week4day4_networking;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ca.judacribz.week4day4_networking.models.GithubProfile;
import ca.judacribz.week4day4_networking.models.datasource.events.OkHttpGithubResponseEvent;
import ca.judacribz.week4day4_networking.models.datasource.remote.OkHttpGithubTask;

public class MainActivity extends AppCompatActivity {

    TextView
            tvLogin,
            tvPublicRepos;

    ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogin = findViewById(R.id.tvLogin);
        tvPublicRepos = findViewById(R.id.tvPublicRepos);
        ivAvatar = findViewById(R.id.ivAvatar);

        new OkHttpGithubTask().execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOkHttpGithubResponseEvent(OkHttpGithubResponseEvent event) {
        GithubProfile githubProfile = event.getGithubProfile();

        tvLogin.setText(githubProfile.getLogin());
        tvPublicRepos.setText(String.valueOf(githubProfile.getPublic_repos()));
        Glide.with(this).load(githubProfile.getAvatar_url()).into(ivAvatar);
    }
}
