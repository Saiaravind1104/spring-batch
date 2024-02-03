package com.spring.batch.springbatch.reader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class FirstItemReader implements ItemReader<Integer> {

	private List<Integer> item=  Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	int i=0;
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		System.out.println("Inside the reader");
		Integer num;
			if(i<item.size()) {
				num=item.get(i);
				i++;
				return num;
			}
			i=0;
		return null;
	}

}
