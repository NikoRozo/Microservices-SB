package com.sb.app.products.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.sb.app.products.models.entity.Product;

public interface ProductsDao extends CrudRepository<Product, Long>{

}
