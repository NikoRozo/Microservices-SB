package com.sb.app.products.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sb.app.products.models.entity.Product;
import com.sb.app.products.models.service.IProductService;

@RestController
public class ProductControl {
	
	@Autowired
	private IProductService preductService;
	
	@GetMapping("/listar")
	public List<Product> listar (){
		return preductService.fineAll();
	}
	
	@GetMapping("/ver/{id}")
	public Product detalle (@PathVariable Long id){
		return preductService.fineById(id);
	}
}
