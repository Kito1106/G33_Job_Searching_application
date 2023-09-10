package my.edu.utar.savedjob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class saved extends AppCompatActivity {

    private JobDatabaseHelper jobDbHelper;
    private RecyclerView savedJobRecyclerView;
    private SavedJobListAdapter savedJobListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        // Initialize the database helper
        jobDbHelper = new JobDatabaseHelper(this);

        // Initialize the RecyclerView
        savedJobRecyclerView = findViewById(R.id.savedJobRecyclerView);

        // Initialize the RecyclerView adapter for saved jobs
        savedJobListAdapter = new SavedJobListAdapter(getSavedJobsFromDatabase());

        // Set a LayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        savedJobRecyclerView.setLayoutManager(layoutManager);

        // Set the adapter for the RecyclerView
        savedJobRecyclerView.setAdapter(savedJobListAdapter);
    }

    // Method to retrieve saved job listings from the database
    @SuppressLint("Range")
    private List<SavedJob> getSavedJobsFromDatabase() {
        // Query the database and retrieve the saved job data
        // You'll need to implement this query based on your database schema
        // For example:
        SQLiteDatabase db = jobDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + JobDatabaseHelper.TABLE_SAVED_JOBS, null);

        List<SavedJob> savedJobs = new ArrayList<>();

        while (cursor.moveToNext()) {
            SavedJob savedJob = new SavedJob();
            savedJob.setJobTitle(cursor.getString(cursor.getColumnIndex(JobDatabaseHelper.COLUMN_JOB_TITLE)));
            savedJob.setCompanyName(cursor.getString(cursor.getColumnIndex(JobDatabaseHelper.COLUMN_COMPANY_NAME)));
            savedJob.setJobLocation(cursor.getString(cursor.getColumnIndex(JobDatabaseHelper.COLUMN_JOB_LOCATION)));
            savedJob.setJobType(cursor.getString(cursor.getColumnIndex(JobDatabaseHelper.COLUMN_JOB_TYPE)));
            savedJob.setSalary(cursor.getString(cursor.getColumnIndex(JobDatabaseHelper.COLUMN_SALARY)));

            savedJobs.add(savedJob);
        }

        cursor.close();
        db.close();

        return savedJobs;
    }
}