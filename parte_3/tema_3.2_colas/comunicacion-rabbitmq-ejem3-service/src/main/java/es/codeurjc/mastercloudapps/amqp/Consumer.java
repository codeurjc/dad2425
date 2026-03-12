package es.codeurjc.mastercloudapps.amqp;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	Logger logger = org.slf4j.LoggerFactory.getLogger(Consumer.class);
	
	
	@RabbitListener(queues = "requests-ejem3", ackMode = "AUTO")
	@SendTo("responses-ejem3")
	public ItemIdResponse received(NewItemIdRequest request) {
		
		logger.info("Assigning id to item: "+request.name());
		
		return new ItemIdResponse(request.name(), (int)(Math.random()*1000));
	}
}
