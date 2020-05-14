package com.sb.app.products.models.service;

import java.util.List;
import com.sb.app.products.models.entity.Product;

public interface IProductService {

	public List<Product> fineAll();

	public Product fineById(Long Id);
	
	public Product save(Product product);
	
	public void deleteById(Long id);
}
