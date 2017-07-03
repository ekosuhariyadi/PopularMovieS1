package com.codangcoding.popularmovies.stage1.internal.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import paperparcel.PaperParcel;

/**
 * Created by eko on 7/2/17.
 */
@PaperParcel
public final class Movie implements Parcelable{

    public static final Creator<Movie> CREATOR = PaperParcelMovie.CREATOR;

    public int id;

    @JsonProperty("poster_path")
    public String posterPath;

    @JsonProperty("original_title")
    public String originalTitle;

    public String overview;

    @JsonProperty("vote_average")
    public double userRating;

    @JsonProperty("release_date")
    public String releaseDate;

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        PaperParcelMovie.writeToParcel(this, dest, flags);
    }
}
