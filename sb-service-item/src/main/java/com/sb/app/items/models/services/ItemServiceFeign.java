package com.sb.app.items.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sb.app.items.clients.ProductClientRest;
import com.sb.app.items.models.Item;
import com.sb.app.items.models.Product;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements IItemService {

	@Autowired
	private ProductClientRest clientFeign;
	
	@Override
	public List<Item> findAll() {
		return clientFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clientFeign.detalle(id), cantidad);
	}

	@Override
	public Product save(Product product) {
		return clientFeign.create(product);
	}

	@Override
	public Product update(Product product, Long id) {
		return clientFeign.update(product, id);
	}

	@Override
	public void delete(Long id) {
		clientFeign.delete(id);
	}

}
