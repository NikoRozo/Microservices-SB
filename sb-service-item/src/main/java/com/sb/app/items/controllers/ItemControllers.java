package com.sb.app.items.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sb.app.items.models.Item;
import com.sb.app.items.models.Product;
import com.sb.app.items.models.services.IItemService;

@RestController
public class ItemControllers {

	@Autowired
	@Qualifier("serviceFeign") //@Qualifier("serviceRestTemplate") // Otra opción con RestTemplate
	private IItemService iItemService;
	
	@GetMapping("/listar")
	public List<Item> listar() {
		return iItemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return iItemService.findById(id, cantidad);
	}
	
	public Item metodoAlternativo(@PathVariable Long id, @PathVariable Integer cantidad) {
		Item item = new Item();
		
		Product product =  new Product();
		product.setId(id);
		product.setNombre("Error");
		product.setPrecio(0.0);
		
		item.setProduct(product);
		item.setCantidad(cantidad);
		
		return item;
	}
}
