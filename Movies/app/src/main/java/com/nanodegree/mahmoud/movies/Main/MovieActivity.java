package com.nanodegree.mahmoud.movies.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nanodegree.mahmoud.movies.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    TextView tv_rate;
    TextView tv_date;
    ImageView iv_poster;
    TextView tv_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "hgfhg", Toast.LENGTH_SHORT).show();
                        MovieActivity.this.finish();
                    }
                }
        );
        tv_rate = (TextView) findViewById(R.id.tv_rate);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_details = (TextView) findViewById(R.id.tv_details);
        iv_poster = (ImageView) findViewById(R.id.iv_poster);

        Intent intent = getIntent();

        String jsonString = intent.getStringExtra("movieId");

        try {
            JSONObject jObj = new JSONObject(jsonString);

            toolbar.setTitle(jObj.getString("title"));
            setSupportActionBar(toolbar);
            Glide.with(this).load("http://image.tmdb.org/t/p/w185/"+jObj.getString("poster_path")).into(iv_poster);
            tv_date.setText(jObj.getString("release_date"));
            tv_rate.setText(jObj.getString("vote_average"));
            tv_details.setText(jObj.getString("overview"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
