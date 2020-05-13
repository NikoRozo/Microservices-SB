package com.sb.app.products.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sb.app.products.models.dao.ProductsDao;
import com.sb.app.products.models.entity.Product;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductsDao productDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> fineAll() {
		return (List<Product>) productDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product fineById(Long id) {
		return productDao.findById(id).orElse(null);
	}

}
