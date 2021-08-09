package com.product.productrestdemo.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Jobs {
	@Id
	private  String id;
	private  String jobName;
	private  String jobGroup;
	private  String cronExp;
	private  String filePath;
	
	public Jobs(String id, String jobName, String jobGroup, String cronExp, String filePath) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.cronExp = cronExp;
		this.filePath = filePath;
	}
	public Jobs(String jobName, String jobGroup, String cronExp, String filePath) {
		this(UUID.randomUUID().toString(), jobName, jobGroup, cronExp, filePath);
	}
	public Jobs() { }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getCronExp() {
		return cronExp;
	}
	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "Jobs [id=" + id + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", cronExp=" + cronExp
				+ ", filePath=" + filePath + "]";
	}
	
}
