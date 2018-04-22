package com.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerLoggerUtil {
	
	private static Logger logger;
	
	public static void info(Class<?> clazz,String message) {
		logger=LoggerFactory.getLogger(clazz);
		logger.info(message);
	}
	
	public static void error(Class<?> clazz,String message) {
		logger=LoggerFactory.getLogger(clazz);
		logger.error(message);
	}
	
	public static void debug(Class<?> clazz,String message) {
		logger=LoggerFactory.getLogger(clazz);
		logger.debug(message);
	}

}
