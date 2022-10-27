package com.airhacks.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.airhacks.enums.SeatMaterial;

@Entity
@Table(name= "seats")
public class Seat {
	
	@Id
	private long id;
	
	@Enumerated(EnumType.STRING)
	private SeatMaterial seatMaterial;
	
	@OneToOne
	private SeatBelt seatBelt;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public SeatMaterial getSeatMaterial() {
		return seatMaterial;
	}
	public void setSeatMaterial(SeatMaterial seatMaterial) {
		this.seatMaterial = seatMaterial;
	}
	public SeatBelt getSeatBelt() {
		return seatBelt;
	}
	public void setSeatBelt(SeatBelt seatBelt) {
		this.seatBelt = seatBelt;
	}
	
	

}
