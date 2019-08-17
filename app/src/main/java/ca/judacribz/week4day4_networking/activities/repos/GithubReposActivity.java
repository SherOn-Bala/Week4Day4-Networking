package ca.judacribz.week4day4_networking.activities.repos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import ca.judacribz.week4day4_networking.R;
import ca.judacribz.week4day4_networking.models.GithubRepo;

import static ca.judacribz.week4day4_networking.activities.GithubProfileActivity.EXTRA_REPOS;

public class GithubReposActivity extends AppCompatActivity {

    RecyclerView rvRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repos);

        rvRepos = findViewById(R.id.rvRepos);

        ArrayList<GithubRepo> repos = getIntent().getParcelableArrayListExtra(EXTRA_REPOS);

        if (repos != null) {
            rvRepos.setLayoutManager(new LinearLayoutManager(this));
            rvRepos.setAdapter(new RepoAdapter(repos));
        }
    }
}
