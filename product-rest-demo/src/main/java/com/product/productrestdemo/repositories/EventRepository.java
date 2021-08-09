package com.product.productrestdemo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.product.productrestdemo.model.Event;


public interface EventRepository extends CrudRepository<Event, String> {

}
