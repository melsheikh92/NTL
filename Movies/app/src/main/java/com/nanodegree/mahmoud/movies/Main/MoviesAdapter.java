package com.nanodegree.mahmoud.movies.Main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nanodegree.mahmoud.movies.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

/**
 * Created by Mahmoud on 25/02/2017.
 */

public class MoviesAdapter extends BaseAdapter {
    private Context mContext;
    LayoutInflater inflater;
    JSONArray mArr;

    public MoviesAdapter(Context c, JSONArray arr) {
        mContext = c;
        mArr = arr;
        inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public int getCount() {
        return mArr.length();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        // ==ImageView imageView;
        View view = inflater.inflate(R.layout.custom_movie, null);
        TextView tv_rate = (TextView) view.findViewById(R.id.tv_movie_rate);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_movie_name);
        ImageView iv_poster = (ImageView) view.findViewById(R.id.iv_movie);

        try {
            JSONObject mov = (JSONObject) mArr.get(position);
            Glide.with(mContext).load("http://image.tmdb.org/t/p/w185/"+mov.getString("poster_path")).into(iv_poster);

            tv_name.setText(mov.getString("title"));
            tv_rate.setText(mov.getString("vote_average"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }





}