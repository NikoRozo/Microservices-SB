package com.sb.app.items.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sb.app.items.models.Item;
import com.sb.app.items.models.Product;
import com.sb.app.items.models.services.IItemService;

@RefreshScope
@RestController
public class ItemControllers {

	private static Logger log = LoggerFactory.getLogger(ItemControllers.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("serviceFeign") //@Qualifier("serviceRestTemplate") // Otra opción con RestTemplate
	private IItemService iItemService;
	
	@Value("${configuration.texto}")
	private String texto;
	
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
	
	@GetMapping("/config")
	public ResponseEntity<?> config(@Value("${server.port}") String port){
		log.info(texto);
				
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("port", port);
		
		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("author.name", env.getProperty("configuration.author.name"));
			json.put("author.email", env.getProperty("configuration.author.email"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product crear(@RequestBody Product product) {
		return iItemService.save(product);
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edit(@RequestBody Product product, @PathVariable Long id) {
		return iItemService.update(product, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delite(@PathVariable Long id) {
		iItemService.delete(id);
	}
	
}
