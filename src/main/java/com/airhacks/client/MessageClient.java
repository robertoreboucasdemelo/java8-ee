package com.airhacks.client;

import java.net.URI;
import java.util.concurrent.locks.LockSupport;
import java.util.Random;
import javax.websocket.ClientEndpoint;
import javax.websocket.Session;

import com.airhacks.control.MessageDecoder;
import com.airhacks.control.MessageEncoder;
import com.airhacks.entity.Message;

@ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class MessageClient {
	
	public void onMessage(Message message) {
		System.out.println("yeah, got new message = " + message);
	}
	
	public static void main(String[] args) {
		// ClientManager in org.glassfish.tyrus.client.ClientManager
		Session session = ClientManager.createClient().connectToServer(MessageClient.class, URI.create("ws://localhost:8080/cars/chat"));
		
		int count = 0;
		
		while(true) {
			LockSupport.parkNanos(2_000_000_000L);
			String content = "Random messages: " + ++count;
			if (new Random().nextBoolean())
				content += " ping";
			
			Message message = new Message("client", content);
			System.out.println("sending message = " + message);
			
			session.getBasicRemote().sendObject(message);
			
		}
	}

}
