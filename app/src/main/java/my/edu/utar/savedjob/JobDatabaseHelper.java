package my.edu.utar.savedjob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JobDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "saved_jobs.db";
    private static final int DATABASE_VERSION = 2; // Update the database version when changing the schema

    public static final String TABLE_SAVED_JOBS = "saved_jobs";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JOB_TITLE = "job_title";
    public static final String COLUMN_COMPANY_NAME = "company_name";
    public static final String COLUMN_JOB_LOCATION = "job_location";
    public static final String COLUMN_JOB_TYPE = "job_type";
    public static final String COLUMN_SALARY = "salary";
    // Add more columns as needed

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_SAVED_JOBS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_JOB_TITLE + " TEXT, " +
                    COLUMN_COMPANY_NAME + " TEXT, " +
                    COLUMN_JOB_LOCATION + " TEXT, " +
                    COLUMN_JOB_TYPE + " TEXT, " +
                    COLUMN_SALARY + " TEXT);";

    public JobDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_JOBS);
        onCreate(db);
    }
}