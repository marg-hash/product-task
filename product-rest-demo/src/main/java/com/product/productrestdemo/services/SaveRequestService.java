package com.product.productrestdemo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.productrestdemo.model.Product;
import com.product.productrestdemo.model.Request;
import com.product.productrestdemo.repositories.EventRepository;
import com.product.productrestdemo.repositories.ProductRepository;
import com.product.productrestdemo.repositories.RequestDetailsRepository;

@Service
public class SaveRequestService {

	@Autowired
 	private final RequestDetailsRepository requestDetailsRepository;
	@Autowired
	private final EventRepository eventRepository;
	@Autowired
	private final ProductRepository productRepository;
	private Request req;
	private List<Product> allProductsList = new ArrayList<>();
	
	public SaveRequestService(RequestDetailsRepository requestDetailsRepository, EventRepository eventRepository,
			ProductRepository productRepository) {
		super();
		this.requestDetailsRepository = requestDetailsRepository;
		this.eventRepository = eventRepository;
		this.productRepository = productRepository;
	}

	public void saveRequestDetails() {
		requestDetailsRepository.save(req.getRequestDetails());
	}
	public void saveProducts() {
		productRepository.saveAll(allProductsList);
	}
	public void saveEvents() {
		eventRepository.saveAll(req.getEvents());
	}

	public Request getReq() {
		return req;
	}
	public void setReq(Request req) {
		this.req = req;
	}

	public List<Product> getAllProductsList() {
		return allProductsList;
	}

	public void setAllProductsList(List<Product> allProductsList) {
		this.allProductsList = allProductsList;
	}
	
}
