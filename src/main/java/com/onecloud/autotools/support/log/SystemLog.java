package com.onecloud.autotools.support.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements the system log capability, providing the methods that record
 * entries in the log, and methods to access the data in the log. The formats
 * and destinations of the entries made by methods in this class are defined by
 * the log4j.properties file in the system configuration. Each entry is assigned
 * to a virtual logger, whose name is the same as the class that requests the
 * entry.
 * 
 * @author sichituk
 */
public class SystemLog implements Log {
	private static boolean traceEnabled = true;

	private static boolean debugEnabled = true;

	private static boolean infoEnabled = true;

	private static boolean warnEnabled = true;
	
	private static boolean errorEnabled = true;
	
	private static SystemLog log;
	
	public void debug(String message) {
		if (debugEnabled) {
			Logger logger = LoggerFactory.getLogger(getLoggingClass());
			logger.debug(message);
		}
	}
	
	/**
	 * Returns a reference to the System Log
	 * @return Returns a reference to the System Log
	 */
	public static Log getLog() {
		if (log == null) {
			log = new SystemLog();
		}
		return log;
	}

	public void trace(String message) {
		if (traceEnabled) {
			Logger logger = LoggerFactory.getLogger(getLoggingClass());
			logger.debug("<trace> " + message);
		}
	}

	public void info(String message) {
		if (infoEnabled) {
			Logger logger = LoggerFactory.getLogger(getLoggingClass());
			logger.info(message);
		}
	}


	public void warn(String message) {
		if (warnEnabled) {
			Logger logger = LoggerFactory.getLogger(getLoggingClass());
			logger.warn(message);
		}
	}


	public void error(String message) {
		if (errorEnabled) {
			Logger logger = LoggerFactory.getLogger(getLoggingClass());
			logger.error(message);
		}
	}

	public void error(Exception e) {
		if (errorEnabled) {
			Logger logger = LoggerFactory.getLogger(getLoggingClass());
			
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
		}
	}
	
	
	/**
	 * Obtains the name of the class that made the log request. Log4J can only
	 * record the name of the class that directly invoked a logger method. In
	 * our case, the name of this class would be recorded all of the time.
	 * 
	 * @return The name of the class that made a call to one of the methods in
	 *         this class.
	 */
	public String getLoggingClass() {
		StackTraceElement[] stack = new Throwable().fillInStackTrace().getStackTrace();
		String loggingClass = "";
		String thisClass = this.getClass().getName();
		for (int i = 0; i < stack.length; i++) {
			loggingClass = stack[i].getClassName();
			if (!loggingClass.equals(thisClass)) {
				break;
			}
		}
		return loggingClass;
	}
	
	/**
	 * @return the debugEnabled
	 */
	public static boolean isDebugEnabled() {
		return debugEnabled;
	}

	/**
	 * @return the infoEnabled
	 */
	public static boolean isInfoEnabled() {
		return infoEnabled;
	}

	/**
	 * @return the traceEnabled
	 */
	public static boolean isTraceEnabled() {
		return traceEnabled;
	}

	/**
	 * @return the warnEnabled
	 */
	public static boolean isWarnEnabled() {
		return warnEnabled;
	}

	/**
	 * @param debugEnabled
	 *            the debugEnabled to set
	 */
	public static void setDebugEnabled(boolean debugEnabled) {
		SystemLog.debugEnabled = debugEnabled;
	}
	

	/**
	 * @return the errorEnabled
	 */
	public static boolean isErrorEnabled() {
		return errorEnabled;
	}

	/**
	 * @param errorEnabled the errorEnabled to set
	 */
	public static void setErrorEnabled(boolean errorEnabled) {
		SystemLog.errorEnabled = errorEnabled;
	}

	/**
	 * @param infoEnabled
	 *            the infoEnabled to set
	 */
	public static void setInfoEnabled(boolean infoEnabled) {
		SystemLog.infoEnabled = infoEnabled;
	}

	/**
	 * @param traceEnabled
	 *            the traceEnabled to set
	 */
	public static void setTraceEnabled(boolean traceEnabled) {
		SystemLog.traceEnabled = traceEnabled;
	}

	/**
	 * @param warnEnabled
	 *            the warnEnabled to set
	 */
	public static void setWarnEnabled(boolean warnEnabled) {
		SystemLog.warnEnabled = warnEnabled;
	}

}
