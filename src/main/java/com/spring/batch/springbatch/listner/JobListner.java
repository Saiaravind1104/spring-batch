package com.spring.batch.springbatch.listner;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListner implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.err.println("Before Job name"+ jobExecution.getJobInstance().getJobName());
		System.err.println("Job Params "+jobExecution.getJobParameters());
		System.err.println("Job Execution context "+jobExecution.getExecutionContext());
		
		jobExecution.getExecutionContext().put("name", "sai");
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.err.println("After Job name"+ jobExecution.getJobInstance().getJobName());
		System.err.println("Job Params "+jobExecution.getJobParameters());
		System.err.println("Job Execution context "+jobExecution.getExecutionContext());
		
	}

}
