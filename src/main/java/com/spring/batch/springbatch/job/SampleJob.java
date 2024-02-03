package com.spring.batch.springbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.spring.batch.springbatch.listner.JobListner;
import com.spring.batch.springbatch.processor.FirstItemProcessor;
import com.spring.batch.springbatch.reader.FirstItemReader;
import com.spring.batch.springbatch.service.SecondTask;
import com.spring.batch.springbatch.writer.FirstItemWritter;

@Configuration
@EnableBatchProcessing
@EnableAsync
@EnableScheduling
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SecondTask secondTask;

	@Autowired
	private JobListner jobListner;

	@Autowired
	private FirstItemProcessor firstItemProcessor;

	@Autowired
	private FirstItemReader firstItemReader;

	@Autowired
	private FirstItemWritter firstItemWritter;

	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First Job")
//				.incrementer(new RunIdIncrementer())
				.start(firstStep())
				.listener(jobListner).build();
	}

	@Bean
	public Step firstStep() {
		return stepBuilderFactory.get("First Step").tasklet(firstTask()).build();
	}

	@Bean
	public Tasklet firstTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("First Step executed");
				return RepeatStatus.FINISHED;
			}
		};
	}

	@Bean
	public Step secondStep() {
		return stepBuilderFactory.get("Second Step").tasklet(secondTask).build();
	}

//    @Bean
//    public Tasklet secondTask() {
//        return  new Tasklet() {
//            
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("Second Step executed");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }

	@Bean
	public Job secondJob() {
		return jobBuilderFactory.get("First Chunk Job")
				.incrementer(new RunIdIncrementer())
				.start(thirdStep())
				.listener(jobListner).build();
	}

	@Bean
	public Step thirdStep() {
		return stepBuilderFactory.get("First Chunk Step").<Integer, Long>chunk(0).reader(firstItemReader)
				.processor(firstItemProcessor).writer(firstItemWritter).build();
	}

}
