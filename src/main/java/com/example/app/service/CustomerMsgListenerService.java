package com.example.app.service;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class CustomerMsgListenerService implements MessageListener{

	@Override
	public void onMessage(Message custMsg) {
		
		System.out.println("On Message" + custMsg);
		try
		{
			if(custMsg instanceof TextMessage )
			{
				TextMessage textMessage = (TextMessage) custMsg;
				System.out.println("Consume the Msg ::: "+ textMessage.getText());
			} else if(custMsg instanceof MapMessage)
			{
				MapMessage mapMessage = (MapMessage) custMsg;
				System.out.println("Map Message ::: " + mapMessage);
			}
			else
			{
				System.out.println("Unsupported Msg Formatted");
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	

}
