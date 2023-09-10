package my.edu.utar.savedjob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class JobDetailActivity extends AppCompatActivity {
    private JobDatabaseHelper JobdbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        JobdbHelper = new JobDatabaseHelper(this);

        // Find the views you want to hide
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView titleTextView = findViewById(R.id.jobTitleTextView);
        TextView companyTextView = findViewById(R.id.companyNameTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        TextView jobTypeTextView = findViewById(R.id.jobTypeTextView);
        TextView salaryTextView = findViewById(R.id.salaryTextView);
        ImageView companyLogoImageView = findViewById(R.id.companyLogoImageView);
        Button applyButton = findViewById(R.id.applyButton);

        // Get the job details passed from HomeActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String jobTitle = extras.getString("job_title");
            String companyName = extras.getString("company_name");
            String jobLocation = extras.getString("job_location");
            String jobDescription = extras.getString("job_description");
            String jobType = extras.getString("job_type");
            String salary = extras.getString("job_salary");

            // Display job details in TextViews
            titleTextView.setText(jobTitle);
            companyTextView.setText(companyName);
            locationTextView.setText(jobLocation);
            descriptionTextView.setText(jobDescription);
            jobTypeTextView.setText("Job Type: " + jobType);
            salaryTextView.setText("Salary: " + salary);

            // Set company logo based on the company name
            int companyLogoResourceId = getCompanyLogoResource(companyName);
            if (companyLogoResourceId != 0) {
                companyLogoImageView.setImageResource(companyLogoResourceId);
            }

            // Add a click listener to the Apply Now button
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //insert job details
                    insertSavedJob();

                    // Show a "Apply Successful" message using a Toast
                    Toast.makeText(JobDetailActivity.this, "Apply Successful", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private int getCompanyLogoResource(String companyName) {
        switch (companyName) {
            case "TITANICOM TECH LIMITED":
                return R.drawable.titanicom;
            case "Quectel Wireless Solutions Co., Ltd.":
                return R.drawable.quectel;
            case "Network Innovations Malaysia Sdn Bhd":
                return R.drawable.network;
            case "iFAST Capital Sdn Bhd":
                return R.drawable.ifast;
            case "Tranglo Sdn Bhd":
                return R.drawable.tranglo;
            case "X-FAB Sarawak Sdn Bhd":
                return R.drawable.xfab;
            default:
                return 0; // Return 0 for no image or a default image
        }
    }

    private void insertSavedJob() {
        // Get the job details from the extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String jobTitle = extras.getString("job_title");
            String companyName = extras.getString("company_name");
            String jobLocation = extras.getString("job_location");
            String jobType = extras.getString("job_type");
            String salary = extras.getString("job_salary");

            // Open the database for writing
            SQLiteDatabase db = JobdbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(JobDatabaseHelper.COLUMN_JOB_TITLE, jobTitle);
            values.put(JobDatabaseHelper.COLUMN_COMPANY_NAME, companyName);
            values.put(JobDatabaseHelper.COLUMN_JOB_LOCATION, jobLocation);

            // Insert the job details into the database
            db.insert(JobDatabaseHelper.TABLE_SAVED_JOBS, null, values);

            // Close the database
            db.close();
        }
    }
}