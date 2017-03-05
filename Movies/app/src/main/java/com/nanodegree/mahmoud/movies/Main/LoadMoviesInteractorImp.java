package com.nanodegree.mahmoud.movies.Main;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Mahmoud on 01/03/2017.
 */

public class LoadMoviesInteractorImp implements LoadMoviesInteractor {



    public void LoadMovies(OnFinishedListener listener) {

    }

    @Override
    public void LoadMovies(LoadMoviesInteractor.OnFinishedListener listener) {

    }

    interface OnFinishedListener {
        void onFinished(List<String> items);

    }




    void getMovies(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{}");
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/4/list/1?sort_by=release_date.asc&api_key=ec298f72dc8c9ad364fda6f08cc2056e&page=1")
                .get()
                .addHeader("content-type", "application/json;charset=utf-8")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {




            }
        });

    }


    }





