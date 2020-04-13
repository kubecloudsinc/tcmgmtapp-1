package com.onecloud.autotools.support.initialization;

/**
 * A component that requires special consideration for initializing itself
 * before being put into service and terminating itself after its service period
 * is over. Startable components are identified via configuration. The StartUp
 * service is responsible managing the lifecycle of these components.
 *
 * @author sichituk
 *
 */
public interface Startable {

    /**
     * Invoked to allow the component to perform its initialization processing
     * before being put into service.
     *
     * @return True if the component initialized itself sucessfully, otherwise
     *         false.
     */
    public boolean startUp();

    /**
     * Invoked to allow the component to perform its termination processing
     * after its service life is over.
     *
     * @return True if the component terminated itself sucessfully, otherwise
     *         false.
     */
    public boolean shutDown();

}