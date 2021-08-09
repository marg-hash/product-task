package com.product.productrestdemo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.product.productrestdemo.model.RequestDetails;


public interface RequestDetailsRepository extends CrudRepository<RequestDetails, String> {

}
