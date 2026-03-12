package es.codeurjc.mastercloudapps.amqp;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	Logger logger = org.slf4j.LoggerFactory.getLogger(Producer.class);
	RabbitTemplate rabbitTemplate;
	
	public Producer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	private int numData;

	@Scheduled(fixedRate = 1000)
	public void sendMessage() {
		
		String data = "Data " + numData++;
		
		logger.info("publishToQueue: '" + data + "'");
				
		rabbitTemplate.convertAndSend("messages", data);
	}
}
