package com.spring.batch.springbatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.batch.springbatch.service.JobService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/triggerJob")
	public String triggerJob() {
		
		/*
		 * Trigerring job by rest api
		 */
		
		jobService.startJob();
		
		return "Job Completed";
		
	}

}
