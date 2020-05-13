package com.sb.app.items.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sb.app.items.models.Item;
import com.sb.app.items.models.services.IItemService;

@RestController
public class ItemControllers {

	@Autowired
	@Qualifier("serviceFeign")
	private IItemService iItemService;
	
	@GetMapping("/listar")
	public List<Item> listar() {
		return iItemService.findAll();
	}
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return iItemService.findById(id, cantidad);
	}
}
