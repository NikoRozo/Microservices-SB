package com.sb.app.products.models.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sb.app.products.models.entity.Product;
import com.sb.app.products.models.service.IProductService;

@RestController
public class ProductControl {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductService preductService;
	
	@GetMapping("/listar")
	public List<Product> listar (){
		return preductService.fineAll().stream().map(product -> {
			//product.setPort(Integer.parseInt(env.getProperty("server.port")));
			product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Product detalle (@PathVariable Long id){
		Product product = preductService.fineById(id);
		//product.setPort(Integer.parseInt(env.getProperty("server.port")));
		product.setPort(port);
		return product;
	}
}
