package com.product.productrestdemo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.product.productrestdemo.model.Jobs;


public interface JobsRepository extends CrudRepository<Jobs, String> {

}
