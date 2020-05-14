package com.sb.app.items.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.sb.app.commons.models.entity.Product;

@FeignClient(name = "service.products")
public interface ProductClientRest {
	// Los metodos en Feign es mas siemple, se deben dejar los mismo metos del controlador de productos (mismo path y nombres)
	@GetMapping("/listar")
	public List<Product> listar();

	@GetMapping("/ver/{id}")
	public Product detalle (@PathVariable Long id);
	
	@PostMapping("/create")
	public Product create (@RequestBody Product product);
	
	@PutMapping("/edit/{id}")
	public Product update (@RequestBody Product product, @PathVariable Long id);
	
	@DeleteMapping("/delete/{id}")
	public Product delete (@PathVariable Long id);
}
