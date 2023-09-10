package my.edu.utar.savedjob;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ViewHolder> {
    private List<JobListing> jobListings;
    private OnItemClickListener onItemClickListener;

    public JobListAdapter(List<JobListing> jobListings) {
        this.jobListings = jobListings;
    }

    public void setJobListings(List<JobListing> allJobListings) {
        this.jobListings = jobListings;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View jobView = inflater.inflate(R.layout.activity_job_list_adapter, parent, false);
        return new ViewHolder(jobView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobListing job = jobListings.get(position);

        holder.jobTitleTextView.setText(job.getTitle());
        holder.companyNameTextView.setText(job.getCompany());
        holder.locationTextView.setText(job.getLocation());
        holder.salaryTextView.setText(job.getSalary());
        holder.jobTypeTextView.setText(job.getJobType());
        holder.companyLogoImageView.setImageResource(job.getCompanyLogo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        // Get the selected job listing
                        JobListing selectedJob = jobListings.get(adapterPosition);

                        // Create an intent to start the JobDetailActivity
                        Intent intent = new Intent(v.getContext(), JobDetailActivity.class);

                        // Pass the selected job listing data to the detail activity
                        intent.putExtra("job_title", selectedJob.getTitle());
                        intent.putExtra("company_name", selectedJob.getCompany());
                        intent.putExtra("job_location", selectedJob.getLocation());
                        intent.putExtra("job_salary", selectedJob.getSalary()); // Pass salary
                        intent.putExtra("job_job_type", selectedJob.getJobType()); // Pass job type
                        intent.putExtra("job_description", selectedJob.getDescription());

                        // Start the detail activity
                        v.getContext().startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobListings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView jobTitleTextView;
        public TextView companyNameTextView;
        public TextView locationTextView;
        public TextView salaryTextView;
        public TextView jobTypeTextView;
        public ImageView companyLogoImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            jobTitleTextView = itemView.findViewById(R.id.jobTitleTextView);
            companyNameTextView = itemView.findViewById(R.id.companyNameTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            salaryTextView = itemView.findViewById(R.id.salaryTextView);
            jobTypeTextView = itemView.findViewById(R.id.jobTypeTextView);
            companyLogoImageView = itemView.findViewById(R.id.companyLogoImageView);
        }
    }
}