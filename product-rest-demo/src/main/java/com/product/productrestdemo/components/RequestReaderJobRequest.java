package com.product.productrestdemo.components;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.product.productrestdemo.model.Jobs;
import com.product.productrestdemo.repositories.JobsRepository;

@Component
public class RequestReaderJobRequest {
	private final JobsRepository jobRepo;
	private Jobs job = null;

	public RequestReaderJobRequest(JobsRepository jobRepo) {
		super();
		this.jobRepo = jobRepo;
		init();
	}

	private void init() {
		Iterable<Jobs> job = this.jobRepo.findAll();
		
		List<Jobs> jobsList = StreamSupport.stream(job.spliterator(), false)
		        .filter(j -> j.getJobName().contentEquals("RequestReaderJob"))
		        .collect(Collectors.toList());
		
		if (jobsList.isEmpty()) {
			this.job = jobsList.get(0);
		}
	}

	public Jobs getJob() {
		return job;
	}
	public void setJob(Jobs job) {
		this.job = job;
	}
	
}
