package com.product.productrestdemo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.product.productrestdemo.model.Product;


public interface ProductRepository extends CrudRepository<Product, String> {

}
