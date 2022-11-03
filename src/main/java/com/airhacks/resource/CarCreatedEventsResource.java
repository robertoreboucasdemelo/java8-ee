package com.airhacks.resource;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import javax.ws.rs.core.HttpHeaders;

import com.airhacks.entity.CarCreated;

@Path("car-created-events")
@Singleton
public class CarCreatedEventsResource {
	
	@Context
	Sse sse;
	private SseBroadcaster sseBroadcaster;
	
	private final List<CarCreated> createdCars = new ArrayList<>();
	
	@PostConstruct
	private void initSseBroadcaster() {
		sseBroadcaster = sse.newBroadcaster();
	}
	
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	@Lock(LockType.READ)
	public void streamCreatedCars(@Context SseEventSink sseEventSink,
			@HeaderParam(HttpHeaders.LAST_EVENT_ID_HEADER) @DefaultValue("-1") int lastEventId) {
		sseBroadcaster.register(sseEventSink);
		
		if (lastEventId >= 0) {
			ressentMissingEvents(sseEventSink, lastEventId);
		}
	}
	
	private void ressentMissingEvents(SseEventSink sseEventSink, int lastEventId) {
		for (int i = lastEventId; i < createdCars.size(); i++) {
			OutboundSseEvent event = createEvent(createdCars.get(i), i+1);
			sseEventSink.send(event);
		}
	}

	@Lock
	public void onCreatedCar(@Observes CarCreated carCreated) {
		sseBroadcaster.broadcast(createEvent(carCreated, createdCars.size() + 1));
		createdCars.add(carCreated);
	}

	private OutboundSseEvent createEvent(CarCreated carCreated, int eventId) {
		return sse.newEventBuilder().id(String.valueOf(eventId)).data(carCreated.getIdentifier()).build();
	}
	

}
