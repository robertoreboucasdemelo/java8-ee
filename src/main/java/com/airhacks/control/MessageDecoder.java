package com.airhacks.control;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.airhacks.entity.Message;

public class MessageDecoder implements Decoder.Text<Message> {

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message decode(String string) throws DecodeException {
		JsonObject jsonObject = Json.createReader(new StringReader(string)).readObject();
		
		String author = jsonObject.getString("author");
		String content= jsonObject.getString("content");
		
		return new Message(author, content);
	}

	@Override
	public boolean willDecode(String string) {
		return true;
	}




}
