package com.sb.app.items.models.services;

import java.util.List;

import com.sb.app.items.models.Item;
import com.sb.app.items.models.Product;

public interface IItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);
	
	public Product save(Product product);
	
	public Product update(Product product, Long id);
	
	public void delete(Long id);
}
