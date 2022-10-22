package com.airhacks.control;

import javax.enterprise.inject.Produces;

import com.airhacks.enums.Color;

public class DefaultColorExposer {
	
	@Produces
	@Diesel
	public Color exposeDefaultColor() {
		//.. some logic
		return Color.RED;
	}

}
