package com.example.mahmoud.mysqlitetutorial.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Mahmoud on 29/03/2017.
 */

public class TodoContract {


    public static final String AUTHORITY = "com.example.mahmoud.mysqlitetutorial";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_TASKS = "tasks";

    public static final class TaskEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();



        public static final String TABLE_NAME = "tasks";

        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PRIORITY = "priority";


    }

}
