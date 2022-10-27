package com.airhacks.entity;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
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
	
	private Color color;
	
	@JsonbProperty("engine")
	private EngineType engineType;
	
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
	
	@Override
	public String toString() {
		return "Car [identifier=" + identifier + ", color=" + color + ", engineType=" + engineType + "]";
	}
	
	

}
