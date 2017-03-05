package com.nanodegree.mahmoud.movies.Main;

import java.util.List;

/**
 * Created by Mahmoud on 01/03/2017.
 */

public interface LoadMoviesInteractor {


    interface OnFinishedListener {
        void onFinished(List<String> items);

    }

    void LoadMovies(OnFinishedListener listener);



}
