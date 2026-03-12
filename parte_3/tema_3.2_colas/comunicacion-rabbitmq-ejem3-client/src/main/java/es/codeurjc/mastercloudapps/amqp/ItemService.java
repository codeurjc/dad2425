package es.codeurjc.mastercloudapps.amqp;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	Logger logger = org.slf4j.LoggerFactory.getLogger(ItemService.class);
	public static final String REQUESTS_QUEUE = "requests-ejem3";
	RabbitTemplate rabbitTemplate;
	ItemRepository itemRepository;
	
	public ItemService(RabbitTemplate rabbitTemplate, ItemRepository itemRepository) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.itemRepository = itemRepository;
	}
	
	public void requestIdToItem(NewItemRequest item) {
		
		itemRepository.save(new Item(item.name(), item.price()));
		rabbitTemplate.convertAndSend(REQUESTS_QUEUE, new NewItemIdRequest(item.name()));
		
	}
	
	@RabbitListener(queues = "responses-ejem3", ackMode = "AUTO")
	public void responseId(ItemIdResponse response) {
		
		Item item = itemRepository.findByName(response.name()).orElseThrow();
		item.setId(response.id());
		itemRepository.save(item);
		
		logger.info("Received response: " + response);
		
	}

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

}
