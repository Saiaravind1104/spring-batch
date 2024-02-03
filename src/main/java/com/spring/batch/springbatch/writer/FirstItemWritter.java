package com.spring.batch.springbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class FirstItemWritter implements ItemWriter<Long> {

	@Override
	public void write(List<? extends Long> items) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside item writter");
		items.stream().forEach(System.out::println);
		
	}

}
