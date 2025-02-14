package com.jobApplication.jobMS.job.clients;

import com.jobApplication.jobMS.job.dto.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="companyMS")
public interface CompanyClient {

    @GetMapping("/company/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
