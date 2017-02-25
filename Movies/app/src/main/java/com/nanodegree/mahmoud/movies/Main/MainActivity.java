package com.nanodegree.mahmoud.movies.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nanodegree.mahmoud.movies.R;

public class MainActivity extends AppCompatActivity implements  Mainview {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
