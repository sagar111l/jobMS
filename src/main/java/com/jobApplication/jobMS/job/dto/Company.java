package com.jobApplication.jobMS.job.dto;



public class Company {

    private Long id;
    private String name;
    private String description;

    private Long jobId;

    private Long reviewId;

    public Company() {
    }

    public Company(Long id, String name, String description, Long jobId, Long reviewId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobId = jobId;
        this.reviewId = reviewId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
}
