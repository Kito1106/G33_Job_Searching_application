package my.edu.utar.savedjob;

public class JobListing {
    private String title;
    private String company;
    private String location;
    private String description;
    private String jobType;
    private String salary;
    private int companyLogo; // Resource ID for company logo

    public JobListing(String title, String company, String location, String description,String jobType,String salary, int companyLogo) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.jobType = jobType;
        this.salary = salary;
        this.companyLogo = companyLogo;
    }

    // Getter and setter methods for each attribute

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(int companyLogo) {
        this.companyLogo = companyLogo;
    }

    // Add getter and setter methods for other attributes as needed
}


