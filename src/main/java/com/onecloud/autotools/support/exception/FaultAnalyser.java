package com.onecloud.autotools.support.exception;

/**
 * Used by Fault Barrier methods to obtain diagnostic information from a fault
 * exception and any nested causitive exceptions.
 * 
 * @author sichituk
 * 
 */
public class FaultAnalyser {

	/**
	 * Returns a String suitable for logging that contains the cause and stack
	 * trace of a fault exception and any nested exceptions that it may contain.
	 * 
	 * @param fault
	 *            The fault exception.
	 * @return The diagnostic information from the fault.
	 */
	public String analyse(Throwable fault) {
		StringBuffer diagnostics = new StringBuffer();
		diagnostics.append("Application Fault Detected: ");
		renderExceptionInfo(diagnostics, fault);
		return (diagnostics.toString());

	}

	/**
	 * Recursive method that appends diagnostic information to a string buffer
	 * for a fault exception and any sequence of nested exceptions.
	 * 
	 * @param diagnostics
	 *            The string buffer to append the diagnostics.
	 * @param fault
	 *            The exception at the current level of nesting.
	 */
	private void renderExceptionInfo(StringBuffer diagnostics, Throwable fault) {
		diagnostics.append(fault.getMessage());

		diagnostics.append("\n Stack Trace: ");
		StackTraceElement[] stackElements = fault.getStackTrace();
		for (int i = 0; i < stackElements.length; i++) {
			diagnostics.append("\n " + stackElements[i]);
		}
		if (fault.getCause() != null) {
			diagnostics.append("\n\n Caused By: ");
			renderExceptionInfo(diagnostics, fault.getCause());
		}

	}

}
