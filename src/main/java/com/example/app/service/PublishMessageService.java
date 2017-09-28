package com.example.app.service;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class PublishMessageService {

	public String publishMessage() throws Exception {
		
		String strRetVal = "Not Successful";
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		
		javax.jms.Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // asynchronus conversation
		
		// Queue
		Destination queue = session.createQueue("app.RequestQ");
		
		// Queue Sender
		MessageProducer messageProducer = session.createProducer(queue);
		
		for(int i=0; i<=10 ; i++)
		{
			/*TextMessage message = session.createTextMessage("Message No:: ::" + i);
			messageProducer.send(message);
			System.out.println("ID" + message.getJMSMessageID());*/	
			
			// sending map message
			
			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setIntProperty("AccountID", i+1000);
			mapMessage.setStringProperty("Name", "Customer"+i);
			
			messageProducer.send(mapMessage);			
		}
		
		connection.close();
		return strRetVal;
	}

}
