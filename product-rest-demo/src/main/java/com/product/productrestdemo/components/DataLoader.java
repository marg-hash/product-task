package com.product.productrestdemo.components;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.product.productrestdemo.model.Jobs;
import com.product.productrestdemo.repositories.JobsRepository;

@Component
public class DataLoader {
	private final JobsRepository jobsRepository;
	
	public DataLoader(JobsRepository jobsRepository) {
		this.jobsRepository = jobsRepository;
	}
	
	@PostConstruct
	private void loadData() {
		this.jobsRepository.save(
			new Jobs("RequestReaderJob", "PresentationGroup", "0 0/2 * * * ?", "C:/request/xml")
		);
	}
	
}
