package com.product.productrestdemo.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.productrestdemo.components.RequestReaderJobResponce;
import com.product.productrestdemo.jobs.ReadRequestJob;
import com.product.productrestdemo.model.Jobs;
import com.product.productrestdemo.repositories.EventRepository;
import com.product.productrestdemo.repositories.JobsRepository;
import com.product.productrestdemo.repositories.ProductRepository;
import com.product.productrestdemo.repositories.RequestDetailsRepository;
import com.product.productrestdemo.services.SaveRequestService;

@RestController
//@RequestMapping("/product")
public class ProductRestDemoAppController {
    //@Autowired
    //private Scheduler scheduler;
	@Autowired
	private final SaveRequestService service;
    
	private final JobsRepository jobRepo;
	private RequestDetailsRepository requestDetailsRepo;
	private EventRepository eventRepo;
	private ProductRepository productRepo;
	private Jobs job = null;
	
    public ProductRestDemoAppController(JobsRepository jobRepo, 
    		RequestDetailsRepository requestDetailsRepo, EventRepository eventRepo, ProductRepository productRepo, SaveRequestService service) {
		super();
		this.jobRepo = jobRepo;
		this.requestDetailsRepo = requestDetailsRepo;
		this.eventRepo = eventRepo;
		this.productRepo = productRepo;
		this.service = service;
		initJob();
	}

	private void initJob() {
		Iterable<Jobs> job = this.jobRepo.findAll();
		
		List<Jobs> jobsList = StreamSupport.stream(job.spliterator(), false)
		        .filter(j -> j.getJobName().contentEquals("RequestReaderJob"))
		        .collect(Collectors.toList());
		
		if (!jobsList.isEmpty()) {
			this.job = jobsList.get(0);
		}
		//scheduleFileReader();
	}
    
	@RequestMapping("/product")
    public ResponseEntity<RequestReaderJobResponce> scheduleFileReader() {
		JobDetail jobDetail = null;
		 try {
			 Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			 //Scheduler scheduler = new SchedulerFactoryBean().getScheduler();
			 
			 jobDetail = buildJobDetail();
			 Trigger cronTrigger = buildJobTrigger(jobDetail);
			 
			 //scheduler.addJob(jobDetail, true);
			 scheduler.start();
			 scheduler.scheduleJob(jobDetail, cronTrigger);
			 RequestReaderJobResponce scheduleEmailResponse = new RequestReaderJobResponce(true, " " + jobDetail.getKey().getName() + " Job Scheduled Successfully.");
			 return ResponseEntity.ok(scheduleEmailResponse);
		 } catch (SchedulerException ex) {
	         RequestReaderJobResponce scheduleEmailResponse = new RequestReaderJobResponce(false, " " + jobDetail.getKey().getName() + " Job scheduling error. Please try later.");
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleEmailResponse);
		 }
    }
   /* public ResponseEntity<FileReaderResponce> scheduleFileReader(@Valid @RequestBody RequestReaderJobRequest joblRequest) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(scheduleEmailRequest.getDateTime(), scheduleEmailRequest.getTimeZone());
            if(dateTime.isBefore(ZonedDateTime.now())) {
                FileReaderResponce scheduleEmailResponse = new FileReaderResponce(false,
                        "dateTime must be after current time");
                return ResponseEntity.badRequest().body(scheduleEmailResponse);
            }

            JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);
            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            FileReaderResponce scheduleEmailResponse = new FileReaderResponce(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email Scheduled Successfully!");
            return ResponseEntity.ok(scheduleEmailResponse);
        } catch (SchedulerException ex) {
            logger.error("Error scheduling email", ex);

            FileReaderResponce scheduleEmailResponse = new FileReaderResponce(false,
                    "Error scheduling email. Please try later!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleEmailResponse);
        }
    }*/

    private JobDetail buildJobDetail() {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("filePath", job.getFilePath());
		jobDataMap.put("service", this.service);
		
        return JobBuilder.newJob(ReadRequestJob.class)
                .withIdentity(job.getJobName(), job.getJobGroup())
                .withDescription("Products Presentation Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail) { //, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
        		  .withIdentity(job.getJobName()+"Trigger", job.getJobGroup())
        		  .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExp())
        				  .withMisfireHandlingInstructionDoNothing())
        		  .forJob(jobDetail)
        		  .startNow()
        		  .build();
    }
}
