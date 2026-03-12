package es.codeurjc.mastercloudapps.amqp;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

	ItemService itemService;
	
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@PostMapping("/items/")
	public NewItemRequest createItem(@RequestBody NewItemRequest item) {
		
		itemService.requestIdToItem(item);
		
		return item;
	}
	
	@GetMapping("/items/")
	public List<Item> getItems() {
		return itemService.findAll();
	}
}
