package ca.judacribz.week4day4_networking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import ca.judacribz.week4day4_networking.R;
import ca.judacribz.week4day4_networking.activities.repos.GithubReposActivity;
import ca.judacribz.week4day4_networking.models.GithubProfile;
import ca.judacribz.week4day4_networking.models.GithubRepo;
import ca.judacribz.week4day4_networking.models.datasource.events.OkHttpGithubResponseEvent;
import ca.judacribz.week4day4_networking.models.datasource.remote.OkHttpGithubTask;

public class GithubProfileActivity extends AppCompatActivity {

    public static final String EXTRA_REPOS = "ca.judacribz.week4day4_networking.activities.EXTRA_REPOS";
    TextView
            tvLogin,
            tvPublicRepos;

    ImageView ivAvatar;
    int dim;

    ArrayList<GithubRepo> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_profile);

        tvLogin = findViewById(R.id.tvLogin);
        tvPublicRepos = findViewById(R.id.tvPublicRepos);
        ivAvatar = findViewById(R.id.ivAvatar);

        dim = getResources().getDisplayMetrics().widthPixels;

        new OkHttpGithubTask().execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.act_repos:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    // EventBus
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOkHttpGithubResponseEvent(OkHttpGithubResponseEvent event) {
        GithubProfile githubProfile = event.getGithubProfile();
        repos = githubProfile.getRepos();

        tvLogin.setText(githubProfile.getLogin());
        tvPublicRepos.setText(String.valueOf(githubProfile.getPublic_repos()));

        tvPublicRepos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GithubReposActivity.class);
                intent.putExtra(EXTRA_REPOS, repos);
                startActivity(intent);
            }
        });

        Glide.with(this)
                .load(githubProfile.getAvatar_url())
                .into(ivAvatar);

//        Picasso
//                .get()
//                .load(githubProfile.getAvatar_url())
//                .resize(dim, dim)
//                .into(ivAvatar);

    }
}
