package com.airhacks.client;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.client.WebTarget;

public class WeatherStation {
	
	private Set<WebTarget> targets;
	
	@Resource
	ManagedExecutorService managed;
	
	@PostConstruct
	private void init() {
		//.
	}
	
	public double getTemperatureForecast() {
		List<CompletionStage<Double>> completionStages = invokeTemperatureTargets();
		
		return completionStages.stream()
				.reduce((l,r) -> l.thenCombine(r, Math::max))
				.map(c -> c.toCompletableFuture().join())
				.orElseThrow(() -> new IllegalStateException("No weather forecast result available"));
	}
	
	private List<CompletionStage<Double>> invokeTemperatureTargets(){
		return targets.stream()
				.map(t -> t.request().rx().get(Double.class))
				.collect(Collectors.toList());
	}

}
