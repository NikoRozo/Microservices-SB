package com.sb.app.items.models.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sb.app.items.models.Item;
import com.sb.app.items.models.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements IItemService {

	@Autowired
	private RestTemplate clientRest;
	
	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays.asList(clientRest.getForObject("http://service.products/listar", Product[].class));
		return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Product product = clientRest.getForObject("http://service.products/ver/{id}", Product.class, pathVariables);
		return new Item(product, cantidad);
	}

}
