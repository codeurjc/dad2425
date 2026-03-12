package es.codeurjc.mastercloudapps.amqp;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	Logger logger = org.slf4j.LoggerFactory.getLogger(Consumer.class);
	
	@RabbitListener(queues = "messagesJson", ackMode = "AUTO")
	public void received(ExampleData data) {
		
		logger.info(data.toString());
	}
}
