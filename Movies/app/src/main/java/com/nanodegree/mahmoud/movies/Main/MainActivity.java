package com.nanodegree.mahmoud.movies.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.nanodegree.mahmoud.movies.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Mainview, AdapterView.OnItemClickListener {
    private ProgressBar progressBar;
    GridView gridview;
    MainPresenter presenter;
    com.github.clans.fab.FloatingActionButton fab;
    static int mfiltertype = 0;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(getApplicationContext());
        progressBar = (ProgressBar) findViewById(R.id.progress);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setOnItemClickListener(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterby(mfiltertype);

            }
        });
        // presenter = new MainPresenterImp();

        LongOperation longOperation = new LongOperation();
        longOperation.execute();


    }


    public void filterby(final int filterType) {
        final String sorttype[] = {"Popularity", "Rate"};
        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(MainActivity.this);
        alertdialogbuilder.setTitle("Sort By  ");

        alertdialogbuilder.setSingleChoiceItems(sorttype, filterType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(getApplicationContext(), which + "", Toast.LENGTH_SHORT).show();
                mfiltertype = which;
                dialog.dismiss();
                LongOperation longOperation = new LongOperation();
                longOperation.execute();

            }
        });


        AlertDialog dialog = alertdialogbuilder.create();

        dialog.show();


    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        // gridview.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        // gridview.setVisibility(View.VISIBLE);
    }

    JSONArray jr;

    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            showProgress();
        }

        @Override
        protected String doInBackground(String... params) {

            String url = "";
            if (mfiltertype == 0) {
                url = "http://api.themoviedb.org/3/movie/popular?api_key=ec298f72dc8c9ad364fda6f08cc2056e";
            } else {
                url = "http://api.themoviedb.org/3/movie/top_rated?api_key=ec298f72dc8c9ad364fda6f08cc2056e";

            }


            StringRequest VolleyReq = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    JSONObject jObject = null;
                    try {
                        jObject = new JSONObject(s);

                        String movies = jObject.getString("results");
                        jr = new JSONArray(movies);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                gridview.setAdapter(new ImageAdapter(getApplicationContext(), jr));
                                hideProgress();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideProgress();
                                AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                                alert.setTitle("Err");
                                alert.setMessage("Error during fetching data .");
                                alert.setPositiveButton("OK", null);
                                alert.show();
                            }
                        });

                    }

                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyError.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgress();
                            AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                            alert.setTitle("Err");
                            alert.setMessage("Error during fetching data .");
                            alert.setPositiveButton("OK", null);
                            alert.show();
                        }
                    });
                }
            });
            queue.add(VolleyReq);

            return "";
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        try {
            intent.putExtra("movieId", jr.get(position).toString());
            startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
