package com.onecloud.autotools.semantic.diagnostics;

/**
 * Allows to put a very specific and descriptive custom diagnostic message 
 * inside ValidationException.
 * 
 * @author sichituk
 *
 */
public class CustomMessageDiagnostic extends Diagnostic {
	
	private String message;

	public CustomMessageDiagnostic(ConditionCode code) {
		super(code);
		// TODO Auto-generated constructor stub
	}
	
	public CustomMessageDiagnostic(String customMessage) {
		super(ConditionCode.ERROR_SYNTAX);
		message = customMessage;
	}
	
	public String toString() {
		return message;
	}
}
