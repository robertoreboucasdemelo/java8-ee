package com.airhacks.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FataLogger {
	// Can Use Some Frameworks like log4j or slf4j
	
	private final static Logger LOGGER = Logger.getLogger(FataLogger.class.getName());
	
	public void fatal(Throwable throwable) {
		LOGGER.log(Level.SEVERE, throwable.getMessage(), throwable);
	}

}
