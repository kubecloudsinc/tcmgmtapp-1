package com.onecloud.autotools.support.exception;


/**
 * Thrown when bad or incomplete parameters are detected for a file storage operation.
 * 
 * @author sichituk
 *
 */
public class BadArgumentsException extends Contingency {
	
    /**
     * 
     */
    private static final long serialVersionUID = -5362333046735882758L;
    public BadArgumentsException () {
        super();
    }
	public BadArgumentsException (String message) {
		super(message);
	}
}
