package com.mongo.example.mongodemo.subscriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.mongo.example.mongodemo.config.MessagingConfig;
import com.mongo.example.mongodemo.models.apimodel.User;

@Component
public class UserConsumer {

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(User user) {
		System.out.println("Message recieved from queue : " + user);
	}
}
