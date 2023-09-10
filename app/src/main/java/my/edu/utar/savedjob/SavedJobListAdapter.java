package my.edu.utar.savedjob;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SavedJobListAdapter extends RecyclerView.Adapter<SavedJobListAdapter.ViewHolder> {
    private List<SavedJob> savedJobs;

    public SavedJobListAdapter(List<SavedJob> savedJobs) {
        this.savedJobs = savedJobs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_job_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedJob savedJob = savedJobs.get(position);

        // Set the data to the views
        holder.jobTitleTextView.setText(savedJob.getJobTitle());
        holder.companyNameTextView.setText(savedJob.getCompanyName());
        holder.jobLocationTextView.setText(savedJob.getJobLocation());
        holder.jobTypeTextView.setText(savedJob.getJobType());
        holder.salaryTextView.setText(savedJob.getSalary());
    }

    @Override
    public int getItemCount() {
        return savedJobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView jobTitleTextView;
        public TextView companyNameTextView;
        public TextView jobLocationTextView;
        public TextView jobTypeTextView;
        public TextView salaryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitleTextView = itemView.findViewById(R.id.jobTitleTextView);
            companyNameTextView = itemView.findViewById(R.id.companyNameTextView);
            jobLocationTextView = itemView.findViewById(R.id.jobLocationTextView);
            jobTypeTextView = itemView.findViewById(R.id.jobTypeTextView);
            salaryTextView = itemView.findViewById(R.id.salaryTextView);
        }
    }
}