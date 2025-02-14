package com.jobApplication.jobMS.job.controller;

import com.jobApplication.jobMS.job.dto.JobDTO;
import com.jobApplication.jobMS.job.model.Job;
import com.jobApplication.jobMS.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;

    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll() {
        return ResponseEntity.ok(jobService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getById(@PathVariable Long id) {
        JobDTO jobDTO = jobService.getById(id);
        if(jobDTO != null)
            return ResponseEntity.ok(jobService.getById(id));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return ResponseEntity.ok("Job Created Successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean flag = jobService.deleteJob(id);
        if(!flag) {
            return new ResponseEntity<>("Job is not available to Delete!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Job is Deleted!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job job){
        boolean flag = jobService.updateJob(id,job);
        if(!flag) {
            return new ResponseEntity<>("Job is not available to Update it!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Job is Updated!");
    }
}
