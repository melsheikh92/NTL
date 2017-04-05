package com.nanodegree.mahmoud.movies.Main.enteties;

/**
 * Created by Mahmoud on 04/03/2017.
 */

public class Movie {

    public Movie(String title, String poster_path, String release_date, String id, String vote_average, String overview) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
    }

    String title;
    String poster_path;
    String release_date;
    String id;

    String vote_average;
    String overview;

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getId() {
        return id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }


}
