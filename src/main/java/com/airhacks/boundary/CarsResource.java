package com.airhacks.boundary;


import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
	
	@Context
	UriInfo uriInfo;
	
	@GET
	public JsonArray retrieveCars(@NotNull @QueryParam("filter") EngineType engineType){
		return carManufacturer.retrieveCars(engineType)
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
	public Response createCar(@Valid @NotNull Specification specification) {
		Car car = carManufacturer.manufactureCar(specification);
		
		URI uri = uriInfo.getBaseUriBuilder()
				.path(CarsResource.class)
				.path(CarsResource.class, "retrieveCar")
				.build(car.getIdentifier());
		
		return Response.created(uri).build();
	}
	
	@GET
	@Path("{id}")
	public Car retrieveCar(@PathParam("id") String identifier) {
		return carManufacturer.retrieveCar(identifier);
	}
}
