package ca.judacribz.week4day4_networking.models;

public class GithubRepo {
    String
            name,
            description,
            created_at,
            language;
    int size;

    public GithubRepo(String name, String description, String created_at, String language, int size) {
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.language = language;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

