package com.airhacks.boundary;

import java.io.IOException;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.airhacks.control.MessageDecoder;
import com.airhacks.control.MessageEncoder;
import com.airhacks.entity.Message;
import com.airhacks.responder.ChatResponder;


@ServerEndpoint(value= "/chat", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class MessageServer {
	
	@Inject
	ChatResponder chatResponder;
	
	public void onMessage(Message message, Session session) throws IOException, EncodeException {
		System.out.println("new message from author " + message.getAuthor());
		Message response = chatResponder.respond(message);
		session.getBasicRemote().sendObject(response);
	}

}
