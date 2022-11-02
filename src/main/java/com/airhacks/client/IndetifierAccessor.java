package com.airhacks.client;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.airhacks.entity.Specification;

@ApplicationScoped
public class IndetifierAccessor {
	
	private Client client;
	private WebTarget target;
	
	private void initClient() {
		client = ClientBuilder.newClient();
		target = client.target("https://cars.example.com/cars/identifications");
	}
	
	public List<String> retrieveCarIdentifications(){
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.get();
		
		GenericType<List<CarIdentifier>> listType = new GenericType<List<CarIdentifier>>() {
		};
		
		return response.readEntity(listType)
				.stream().map(CarIdentifier::getIdentifier)
				.collect(Collectors.toList());
	}
	
	public String retrieveCarIdentification(Specification specification) {
		JsonObject entity = buildRequestBody(specification) ;
		Response response = sendRequest(entity);
		return (extractIdentifier(response));
	}
	
	private Response sendRequest(JsonObject entity) {
		return target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json(entity));
	}
	
	private String extractIdentifier(Response response) {
		return response.readEntity(JsonObject.class).getString("identifier");
	}
	
	private JsonObject buildRequestBody(Specification specification) {
		return Json.createObjectBuilder()
				.add("engine", specification.getEngineType().name())
				.build();
	}
	
	@PreDestroy
	private void closeClient() {
		client.close();
	}
	
	private class CarIdentifier {
		private String identifier;

		public String getIdentifier() {
			return identifier;
		}

		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}
	}

}
