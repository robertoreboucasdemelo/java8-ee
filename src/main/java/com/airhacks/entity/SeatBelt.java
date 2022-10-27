package com.airhacks.entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.airhacks.enums.SeatBeltModel;

@Embeddable
public class SeatBelt {
	
	@Enumerated(EnumType.STRING)
	private SeatBeltModel model;
	
	public void open() {
		
	}
	
	public void close() {
		
	}
	
	
}
