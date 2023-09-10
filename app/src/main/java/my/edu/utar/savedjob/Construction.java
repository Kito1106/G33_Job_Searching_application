package my.edu.utar.savedjob;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class Construction extends AppCompatActivity {

    private RecyclerView jobRecyclerView;
    private JobListAdapter jobListAdapter;
    private Button seeAllButton;
    private boolean isShowingAll = false; // Track if all job listings are shown
    private List<JobListing> allJobListings; // Store all job listings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);

        jobRecyclerView = findViewById(R.id.jobRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        jobRecyclerView.setLayoutManager(layoutManager);

        seeAllButton = findViewById(R.id.seeAllButton);

        // Create a list of all job listings
        allJobListings = createJobListings();

        // Create a list to initially display only three job listings
        List<JobListing> displayedJobListings = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            displayedJobListings.add(allJobListings.get(i));
        }

        jobListAdapter = new JobListAdapter(displayedJobListings);
        jobRecyclerView.setAdapter(jobListAdapter);

        // Set an item click listener for the RecyclerView
        jobListAdapter.setOnItemClickListener(new JobListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click here
                JobListing clickedJob = displayedJobListings.get(position);
                Log.d("RecyclerViewClick", "Item clicked: " + clickedJob.getTitle());

                // Create an intent to start the JobDetailActivity and pass the job details
                Intent intent = new Intent(Construction.this, JobDetailActivity.class);
                intent.putExtra("job_title", clickedJob.getTitle());
                intent.putExtra("company_name", clickedJob.getCompany());
                intent.putExtra("job_location", clickedJob.getLocation());
                intent.putExtra("job_job_type", clickedJob.getJobType()); // Pass job type
                intent.putExtra("job_salary", clickedJob.getSalary()); // Pass salary
                intent.putExtra("job_description", clickedJob.getDescription());
                startActivity(intent);
            }
        });

        // Set a click listener for the "See All" button
        seeAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowingAll) {
                    // If not showing all, update the displayedJobListings to show all job listings
                    displayedJobListings.clear();
                    displayedJobListings.addAll(allJobListings);
                    jobListAdapter.notifyDataSetChanged();
                    seeAllButton.setText("Show Less");
                } else {
                    // If showing all, update the displayedJobListings to show only three job listings
                    displayedJobListings.clear();
                    for (int i = 0; i < 3; i++) {
                        displayedJobListings.add(allJobListings.get(i));
                    }
                    jobListAdapter.notifyDataSetChanged();
                    seeAllButton.setText("See All");
                }
                isShowingAll = !isShowingAll;
            }
        });


    }

    private List<JobListing> createJobListings() {
        List<JobListing> jobListings = new ArrayList<>();

        JobListing job1 = new JobListing(
                "Technical Service Assistant",
                "TITANICOM TECH LIMITED",
                "Kuala Lumpur",
                "Job Description:\n" +
                        "\n" +
                        "1. Standby on-call and provide first-level contact and incident resolution for customers and escalate major incidents as appropriate;\n" +
                        "\n" +
                        "2. Preparing SOP (standard operating procedure) of onsite support case and seeking internal approval;\n" +
                        "\n" +
                        "3. Urgent onsite support when it is required within 4 hours for the highest SLA response;\n" +
                        "\n" +
                        "4. Troubleshooting, diagnosing, and resolving server hardware problems;",
                "Internship", // Set the job type here
                "RM800-RM1000", // Set the salary here
                R.drawable.titanicom // Replace 'job' with the appropriate drawable resource
        );

        JobListing job2 = new JobListing(
                "IT Specialist",
                "Quectel Wireless Solutions Co., Ltd.",
                "Penang",
                "Responsibilities:\n" +
                        "\n" +
                        "1.Responsible for the daily helpdesk technical support of the company and the installation of PC operating system and common office software；\n" +
                        "\n" +
                        "2.Responsible for the management, maintenance and daily inspection of the company's computer room, related network equipment (router, core switch, AC/AD) and server；\n" +
                        "\n" +
                        "3.Responsible for the daily maintenance of the company's network, optimization of the company's wifi signal, and guarantee the normal operation of the staff's office network.",
                "Full Time",
                "RM5000",
                R.drawable.quectel
        );

        JobListing job3 = new JobListing(
                "System Administrator",
                "Network Innovations Malaysia Sdn Bhd",
                "Kuala Lumpur",
                "Duties and responsibilities:\n" +
                        "\n" +
                        "Server and Workstation Operational Administration\n" +
                        "1) Support the installation, maintenance, and sustainment of approximately 300 workstations and servers globally – physical and virtual\n" +
                        "2) Build test and deploy applications, workstations and servers ensuring security standards are maintained\n" +
                        "3) Provide reports on assets and patching status and any trends seen to help improve the health of the environment.",
                "Internship",
                "RM800-1200",
                R.drawable.network
        );

        JobListing job4 = new JobListing(
                "Systems Engineer",
                "iFAST Capital Sdn Bhd",
                "Kuala Lumpur",
                "Job Description:\n" +
                        "\n" +
                        "iFAST is seeking an experienced, technical, and enthusiastic Systems Engineer. Systems Engineers are required to establish business needs, challenges, and opportunities within the current systems/infrastructure and future needs and direction of iFAST.\n" +
                        "\n" +
                        "Our goal is to deliver quality and timely information technology services to support the business needs of iFAST.",
                "Fulltime",
                "RM3000",
                R.drawable.ifast
        );

        JobListing job5 = new JobListing(
                "IT Project Manager",
                "Tranglo Sdn Bhd",
                "Ipoh",
                "Responsibilities:\n" +
                        "\n" +
                        "1) Lead teams in refining business requirements, designing, developing, enhancing, and implementing applications.\n" +
                        "\n" +
                        "2) Participate in project initiation, planning, execution, and closure.\n" +
                        "\n" +
                        "3) Create and maintain project documents including charter, change request log, risk and issue logs.\n" +
                        "\n" +
                        "4) Conduct risk analysis for each project.\n",
                "Full Time",
                "RM8000",
                R.drawable.tranglo
        );

        JobListing job6 = new JobListing(
                "Data Analyst",
                "X-FAB Sarawak Sdn Bhd",
                "Kuching",
                "What we do:\n" +
                        "\n" +
                        "We are passionate about making chips that bring value to people’s lives. We manufacture semiconductor devices that help to save lives, connect people and drive cleaner transportation. Without knowing you come across products that have chips made at X-FAB inside every day: in your car, your cell phone or when you get an ultrasound examination.",
                "Internship",
                "RM1300",
                R.drawable.xfab
        );

        // Add all the job listings to the list
        jobListings.add(job1);
        jobListings.add(job2);
        jobListings.add(job3);
        jobListings.add(job4);
        jobListings.add(job5);
        jobListings.add(job6);

        return jobListings;
    }

}






