package com.onecloud.autotools.support.exception;

/**
 * Represents a planned condition demanding an alternative response from a
 * method, expressed in terms of the method's intended purpose. The caller of
 * the method expects these alternate flows and has a strategy for coping with
 * them. A Contingency is an expected exceptional condition that is part of a
 * class API. All such exceptions should subclass this class.
 * 
 * @author sichituk
 */
public abstract class Contingency extends Exception {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1791254954397695313L;

	/**
     * Creates an instance of the class.
     */
    public Contingency() {
        super();
    }

    /**
     * Creates an instance of the class with a message that may be used for
     * logging purposes.
     * 
     * @param message
     *        A message to include with the Contingency, although this practice
     *        is not encouraged, and should not be used to convey significant
     *        supplemental information to the receiver of the Contingency.
     */
    public Contingency(String message) {
        super(message);
    }
}
