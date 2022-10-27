package com.airhacks.entity;

import java.util.HashSet;
import java.util.Set;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.airhacks.enums.Color;
import com.airhacks.enums.EngineType;

@Entity
@Table(name="cars")
@NamedQuery(name = Car.FIND_ALL, query = "select c from Car c")
public class Car {
	
	public static final String FIND_ALL = "Car.findAll";
	
	@Id
	private String identifier;
	
	@Enumerated(EnumType.STRING)
	private Color color;
	
	
	//@JsonbProperty("engine")
	@Enumerated(EnumType.STRING)
	private EngineType engineType;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "car", nullable = false)
	private Set<Seat> seats = new HashSet<>();
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public EngineType getEngineType() {
		return engineType;
	}
	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}	
	public Set<Seat> getSeats() {
		return seats;
	}
	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
	
	@Override
	public String toString() {
		return "Car [identifier=" + identifier + ", color=" + color + ", engineType=" + engineType + "]";
	}
	
	

}
