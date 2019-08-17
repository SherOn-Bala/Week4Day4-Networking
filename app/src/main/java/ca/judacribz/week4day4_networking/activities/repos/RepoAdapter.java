package ca.judacribz.week4day4_networking.activities.repos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import ca.judacribz.week4day4_networking.R;
import ca.judacribz.week4day4_networking.activities.repos.RepoAdapter.*;
import ca.judacribz.week4day4_networking.models.GithubRepo;

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {
    private ArrayList<GithubRepo> repos;

    RepoAdapter(ArrayList<GithubRepo> repos) {
        this.repos = repos;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_repo,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.setViews(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {

        TextView
                tvName,
                tvDescription,
                tvCreatedAt,
                tvLanguage,
                tvSize;
        View itemView;

        RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvLanguage = itemView.findViewById(R.id.tvLanguage);
            tvSize = itemView.findViewById(R.id.tvSize);
        }

        void setViews(GithubRepo repo) {
            tvName.setText(repo.getName());
            final String desc = repo.getDescription();

            tvDescription.setText(
                    (desc != null && !desc.isEmpty()) ?
                            desc :
                            itemView.getContext().getString(R.string.na)
            );

            tvCreatedAt.setText(getFormatString(R.string.created_at_s, repo.getCreated_at()));
            tvLanguage.setText(getFormatString(R.string.language_s, repo.getLanguage()));
            tvSize.setText(getFormatString(R.string.size_s, String.valueOf(repo.getSize())));
        }

        private String getFormatString(int strFormatId, String string) {
            return String.format(
                    Locale.US,
                    itemView.getContext().getString(strFormatId),
                    string
            );
        }
    }
}
