package com.jobApplication.jobMS.job.mapper;

import com.jobApplication.jobMS.job.dto.Company;
import com.jobApplication.jobMS.job.dto.JobDTO;
import com.jobApplication.jobMS.job.dto.Review;
import com.jobApplication.jobMS.job.model.Job;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobCompanyDto(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getCompanyId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
