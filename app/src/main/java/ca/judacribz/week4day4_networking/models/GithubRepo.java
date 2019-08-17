package ca.judacribz.week4day4_networking.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GithubRepo implements Parcelable {
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

    protected GithubRepo(Parcel in) {
        name = in.readString();
        description = in.readString();
        created_at = in.readString();
        language = in.readString();
        size = in.readInt();
    }

    public static final Creator<GithubRepo> CREATOR = new Creator<GithubRepo>() {
        @Override
        public GithubRepo createFromParcel(Parcel in) {
            return new GithubRepo(in);
        }

        @Override
        public GithubRepo[] newArray(int size) {
            return new GithubRepo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(created_at);
        parcel.writeString(language);
        parcel.writeInt(size);
    }
}

