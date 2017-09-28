package com.example.app.service;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class SubscribeMessageService {
	
	/*@Autowired
	private CustomerMsgListenerService customerMsgListenerService;*/
	
	// subscriber need to have a listener class which acts like consumer. Consumer binds to queue. Logic is executed automatically
	
	public String subscribeMessage() throws Exception {

		String strRetVal = "Not Successful";
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		
		javax.jms.Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // asynchronus conversation
		
		// Queue
		Destination queue = session.createQueue("app.RequestQ");
		
		// Queue Consumer
		MessageConsumer messageConsumer = session.createConsumer(queue);
		/*messageConsumer.setMessageListener(new CustomerMsgListenerService());*/
		
		Message message = messageConsumer.receive();
		
		while(message != null)
		{
			if(message instanceof TextMessage )
			{
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Consume the Msg ::: "+ textMessage.getText());
			} else if(message instanceof MapMessage)
			{
				MapMessage mapMessage = (MapMessage) message;
				System.out.println("Map Message ::: " + mapMessage);
			}
			else
			{
				System.out.println("Unsupported Msg Formatted");
			}
			
			message = messageConsumer.receive();
		}
		
		
		connection.close();
		
		return strRetVal;
	}
}
