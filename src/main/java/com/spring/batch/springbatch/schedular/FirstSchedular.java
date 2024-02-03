package com.spring.batch.springbatch.schedular;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FirstSchedular {
	
	@Autowired
	JobLauncher jobLauncher;
	
	
	@Qualifier("secondJob")
	@Autowired
	Job secondJob;

	/*
	 * Spring schedular is used to schedule
	 * the batch for every 1min based upon cron
	 */
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void firstSchedular() {
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
