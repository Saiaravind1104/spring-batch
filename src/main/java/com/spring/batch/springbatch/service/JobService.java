package com.spring.batch.springbatch.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class JobService {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Qualifier("firstJob")
	@Autowired
	Job firstJob;
	
	@Qualifier("secondJob")
	@Autowired
	Job secondJob;
	
	@Async
	public void startJob() {
		try {
			Map<String,JobParameter> parameters=new HashMap<>();
			parameters.put("currentTime",new JobParameter(System.currentTimeMillis()) );
			
			JobParameters jobParameters=new JobParameters(parameters);
			
			jobLauncher.run(secondJob, jobParameters);
		}catch(Exception exception) {
			System.err.print("Error in JobService");
		}
	}

}
