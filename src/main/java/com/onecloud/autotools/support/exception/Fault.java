package com.onecloud.autotools.support.exception;


/**
 * Represents an unplanned condition that prevents a method from achieving its
 * intended purpose that cannot be described without referencing the method's
 * internal implementation. A Fault is an unplanned error condition that is not
 * part of any class' API - something is wrong inside the application. Its
 * purpose is to record diagnostic information. This class should have no
 * subclasses outside of Unit testing.
 * <p>
 * Fault exceptions should not be caught except by the few designated Fault
 * Barrier methods defined in the architecture.
 * 
 * @author sichituk
 */
public class Fault extends RuntimeException {
	
    /**
     * 
     */
    private static final long serialVersionUID = -8608056886523866799L;

    /**
     * Constructs a Fault exception with a simple diagnostic message.
     * 
     * @param message
     *        A descriptive message explaining the reason for the Fault.
     */
    public Fault(String message) {
        super(message);
    }

    /**
     * Constructs a Fault exception with a simple diagnostic message along with
     * a causing exception. 
     * 
     * @param message
     *        A descriptive message explaining the reason for the Fault.
     * @param cause
     *        An exception captured to help explain the cause of the Fault. 
     */
    public Fault(String message, Throwable cause) {
        super(message, cause);
    }
}