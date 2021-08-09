package com.product.productrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.product.productrestdemo.jobs.ReadRequestJob;

@SpringBootApplication
public class ProductRestDemoApplication {
	private static ApplicationContext ctx;
	public static void main(String[] args) {
		//SpringApplication.run(ProductRestDemoApplication.class, args);
		ctx = SpringApplication.run(ProductRestDemoApplication.class, args);
		ReadRequestJob reportService = ctx.getBean(ReadRequestJob.class);
	}

}
