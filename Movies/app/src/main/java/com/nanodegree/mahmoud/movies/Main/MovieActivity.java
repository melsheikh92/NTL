package com.nanodegree.mahmoud.movies.Main;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nanodegree.mahmoud.movies.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {
    android.support.v7.widget.Toolbar toolbar;
    TextView tv_rate;
    TextView tv_date;
    ImageView iv_poster;
    TextView tv_details;
    String jsonString;
    final String KEY_OF_SELECTED_MOVIE = "movieId";
    final String KEY_OF_KEEP_MOVIE = "MYOBJ";

    Button btnvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_rate = (TextView) findViewById(R.id.tv_rate);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_details = (TextView) findViewById(R.id.tv_details);
        iv_poster = (ImageView) findViewById(R.id.iv_poster);
        btnvideo= (Button) findViewById(R.id.btnvideo);
        btnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovieActivity.this, VideoActivity.class));


            }
        });
        Intent intent = getIntent();
        if (intent.hasExtra("movieId")) {
            jsonString = intent.getStringExtra(KEY_OF_SELECTED_MOVIE);
        } else {
            if (jsonString == null) {
                savedInstanceState.getString(KEY_OF_KEEP_MOVIE);
            }
        }


        try {
            JSONObject jObj = new JSONObject(jsonString);

            toolbar.setTitle(jObj.getString("title"));
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.back);
            toolbar.setNavigationOnClickListener(this);
            Glide.with(this).load("http://image.tmdb.org/t/p/w185/" + jObj.getString("poster_path")).into(iv_poster);
            tv_date.setText(jObj.getString("release_date"));
            tv_rate.setText(jObj.getString("vote_average"));
            tv_details.setText(jObj.getString("overview"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(KEY_OF_KEEP_MOVIE, jsonString);

    }

    @Override
    public void onClick(View v) {
        finish();
    }



}
