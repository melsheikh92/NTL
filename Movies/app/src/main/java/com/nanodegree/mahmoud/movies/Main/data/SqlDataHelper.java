package com.nanodegree.mahmoud.movies.Main.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mahmoud on 05/04/2017.
 */

public class SqlDataHelper extends SQLiteOpenHelper {


    public SqlDataHelper(Context context) {

        super(context, FavoriteContract.DATABASE_NAME, null, FavoriteContract.DB_VERSION);


    }

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + FavoriteContract.TABLE_NAME + " (" +
                    FavoriteContract.KEY_ID + "integer PRIMARY KEY," +
                    FavoriteContract.KEY_NAME + "," +
                    FavoriteContract.KEY_POSTER + "," +
                    FavoriteContract.KEY_VOTE_AVG + "," +
                    FavoriteContract.KEY_RELEASE_DATE + "," +
                    " UNIQUE (" + FavoriteContract.KEY_ID + "));";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteContract.TABLE_NAME);
        onCreate(db);
    }
}
