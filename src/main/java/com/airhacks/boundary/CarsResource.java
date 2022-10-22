package com.airhacks.boundary;


import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonCollectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.airhacks.entity.Car;
import com.airhacks.entity.Specification;
import com.airhacks.enums.Color;
import com.airhacks.enums.EngineType;

@Path("cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarsResource {
	
	@Inject
	CarManufacturer carManufacturer;
	
	@GET
	public JsonArray retrieveCars(){
		return carManufacturer.retrieveCars()
				.stream()
				.map(c -> Json.createObjectBuilder()
						.add("color", c.getColor().name())
						.add("engine", c.getEngineType().name())
						.add("id", c.getIdentifier())
						.add("hello", "value")
						.build())
				.collect(JsonCollectors.toJsonArray());
	}
	
	@POST
	public void createCar(JsonObject jsonObject) {
		Color color = Color.valueOf(jsonObject.getString("color"));
		EngineType engine= EngineType.valueOf(jsonObject.getString("engine"));
		carManufacturer.manufactureCar(new Specification(color, engine));
	}
}
