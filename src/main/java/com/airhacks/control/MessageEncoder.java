package com.airhacks.control;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.airhacks.entity.Message;

public class MessageEncoder implements Encoder.Text<Message> {

	@Override
	public void init(EndpointConfig config) {
	
	}

	@Override
	public void destroy() {
	
	}

	@Override
	public String encode(Message message) throws EncodeException {
		return Json.createObjectBuilder()
				.add("author", message.getAuthor())
				.add("content", message.getContent())
				.build().toString();
	}

}
