package com.jobApplication.jobMS.job.service;


import com.jobApplication.jobMS.job.clients.CompanyClient;
import com.jobApplication.jobMS.job.clients.ReviewClient;
import com.jobApplication.jobMS.job.dto.Company;
import com.jobApplication.jobMS.job.dto.JobDTO;
import com.jobApplication.jobMS.job.dto.Review;
import com.jobApplication.jobMS.job.mapper.JobMapper;
import com.jobApplication.jobMS.job.model.Job;
import com.jobApplication.jobMS.job.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{

    private final JobRepository jobRepository;
    private final RestTemplate restTemplate;
    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    private static final String companyMSUrl = "http://companyMS/company/";
    private static final String reviewMSUrl = "http://reviewMS/reviews?companyId=";

    @Autowired
    public JobServiceImpl(JobRepository jobRepository,RestTemplate restTemplate,
                          CompanyClient companyClient,ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.restTemplate = restTemplate;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<JobDTO> getAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobCompanyList = new ArrayList<>();
        //RestTemplate restTemplate = new RestTemplate();
        for(Job job : jobs) {
            /*Company company = restTemplate.getForObject(companyMSUrl +job.getCompanyId(), Company.class);
            ResponseEntity<List<Review>> reviews = restTemplate.exchange(reviewMSUrl + job.getCompanyId(),
                    HttpMethod.GET,null, new ParameterizedTypeReference<List<Review>>() {});*/
            Company company = companyClient.getCompany(job.getCompanyId());
            List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
            jobCompanyList.add(JobMapper.mapToJobCompanyDto(job,company,reviews));
        }

        return jobCompanyList;
    }

    @Override
    public JobDTO getById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertJobToDto(job);
    }

    @Override
    public void createJob(Job job) {
        /*Long companyId = job.getCompany().getId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));

        // Associate the fetched company with the job
        job.setCompany(company);*/
        jobRepository.save(job);
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
           Job jobUpdate = jobOptional.get();
           jobUpdate.setTitle(job.getTitle());
           jobUpdate.setDescription(job.getDescription());
           jobUpdate.setMaxSalary(job.getMaxSalary());
           jobUpdate.setMinSalary(job.getMinSalary());
           jobUpdate.setLocation(job.getLocation());
           //jobUpdate.setCompany(job.getCompany());
            jobUpdate.setCompanyId(job.getCompanyId());
           jobRepository.save(jobUpdate);
           return true;
        }

        return false;
    }

    private JobDTO convertJobToDto(Job job){
        /*Company company = restTemplate.getForObject(companyMSUrl +job.getCompanyId(), Company.class);
        ResponseEntity<List<Review>> reviews = restTemplate.exchange(reviewMSUrl + job.getCompanyId(),
                HttpMethod.GET,null, new ParameterizedTypeReference<List<Review>>() {});*/
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        return JobMapper.mapToJobCompanyDto(job,company,reviews);
    }
}
