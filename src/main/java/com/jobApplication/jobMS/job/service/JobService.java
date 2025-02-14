package com.jobApplication.jobMS.job.service;




import com.jobApplication.jobMS.job.dto.JobDTO;
import com.jobApplication.jobMS.job.model.Job;

import java.util.List;

public interface JobService {

    public List<JobDTO> getAll();

    public JobDTO getById(Long id);

    public void createJob(Job job);

    public boolean deleteJob(Long id);

    public boolean updateJob(Long id, Job job);

}
