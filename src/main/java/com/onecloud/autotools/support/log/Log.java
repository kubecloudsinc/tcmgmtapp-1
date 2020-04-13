/**
 * 
 */
package com.onecloud.autotools.support.log;


/**
 * Provides the capability to record events in the system as they occur, and the ability to classify different events
 * according to their intended audience and importance.
 * 
 * @author sichituk
 */
public interface Log {

    /**
     * Records an informational event - Journaling information about routine successful processing events. Normally
     * expressed in terms of business domain concepts. Includes confirmation of the successful completion of each
     * submitted request. Also includes low frequency application-scope events such as initialization and termination
     * confirmations.
     * 
     * @param msg
     *        The textual description of the event.
     */
    public void info(String msg);

    /**
     * Records a debugging event - Details of internal processing often exposing details of the application�s internal
     * design. May be expressed in terms of Java primitives, classes, fields, methods, exceptions, status codes, etc.
     * Used during the development and testing process to trace events relevant to the application�s internal processing
     * flow.
     * 
     * @param msg
     *        The textual description of the event.
     */
    public void debug(String msg);
    public void trace(String msg);

    /**
     * Records a routine request failure - Normally expressed in terms of a request parameters and domain concepts.
     * Records errors caused by invalid requests, invalid users, etc. These messages help correct and resolve errors
     * made by clients of the application.
     * 
     * @param msg
     *        The textual description of the event.
     */
    public void warn(String msg);

    /**
     * Records a fault event - a serious unexpected failure in the system that requires attention either by
     * administrative action or by fixing the system itself. For example, no response from an external database
     * (requires administrative action), assertion failure (requires programmer analysis and action), etc. Usually
     * expressed in terms of the internal design of the application.
     * 
     * @param msg
     *        The textual description of the event.
     */
    public void error(String msg);
    
    public void error(Exception e);

   
    /**
     * Obtains the name of the class that made the log request. 
     * 
     * @return The name of the class that made a call to one of the methods in the logging object.
     */
    public String getLoggingClass();
}
