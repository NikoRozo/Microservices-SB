package com.sb.app.items.models.services;

import java.util.List;

import com.sb.app.items.models.Item;

public interface IItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer cantidad);
}
