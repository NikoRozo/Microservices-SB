package com.sb.app.products.models.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	private IProductService productService;
	
	@GetMapping("/listar")
	public List<Product> listar (){
		return productService.fineAll().stream().map(product -> {
			//product.setPort(Integer.parseInt(env.getProperty("server.port")));
			product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Product detalle (@PathVariable Long id){
		Product product = productService.fineById(id);
		//product.setPort(Integer.parseInt(env.getProperty("server.port")));
		product.setPort(port);
		
		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return product;
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product crear (@RequestBody Product product){
		return productService.save(product);
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product editar (@RequestBody Product product, @PathVariable Long id){
		Product productDb = productService.fineById(id);
		
		productDb.setNombre(product.getNombre());
		productDb.setPrecio(product.getPrecio());
		
		return productService.save(productDb);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar (@PathVariable Long id){
		productService.deleteById(id);
	}
}
