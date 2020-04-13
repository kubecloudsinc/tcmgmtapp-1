package com.onecloud.autotools.support.initialization;

import com.onecloud.autotools.support.configuration.*;
import com.onecloud.autotools.support.exception.Assertion;
import com.onecloud.autotools.support.log.Log;
import com.onecloud.autotools.support.log.SystemLog;

import java.util.*;

/**
 * Responsible for invoking initialization and termination processing for an
 * arbitrary set of configured components in the system. This service is
 * expected to be called by listeners in the access tier that detect application
 * start-up and shut-down.
 * <p>
 * The service is driven by its configuration, which maps the factory object
 * type identifier of each component that requires initializtion with a priority
 * value that determines the order that the service performs initialization. Low
 * values preceed higher values. Components assigned to the same value are
 * initialized in arbitrary order, but after components with lower values and
 * before components with higher values.
 * <p>
 * Components that are handled by this service must be eligible for building by
 * the object factory, and they also must implement the Startable interface.
 *
 * @author sichituk
 *
 */
public class StartupService implements Startable {

    private static List startedComponents = new ArrayList();

    private Map priorityMap = new TreeMap();

    /**
     * Looks at the configuration to find components to start, orders them by
     * priority (from lowest to highest), creates instances via the object
     * factory, and calls their startup() methods. Generates informational log
     * entries that record the progress of the initialization sequence.
     *
     * @return True if all configured components were started sucessfully,
     *         otherwise false.
     *
     */
    public synchronized boolean startUp() {
        int attemptedStarts = 0;
        int successfulStarts = 0;
        if (startedComponents.isEmpty()) {
            Log log = getLog();
            log.info("G5 System Initialization Sequence Started. System Environment = "
                    + getEnvironment().getExecutionEnvironment() + ".");
            ObjectFactory factory = getFactory();
            Configuration config = getConfiguration();

            String[] objectTypes = config.getKeys();
            for (int i = 0; i < objectTypes.length; i++) {
                Integer priority = new Integer(config.getIntegerValue(objectTypes[i]));
                List typeList = (List) priorityMap.get(priority);
                if (typeList == null) {
                    typeList = new ArrayList();
                    priorityMap.put(priority, typeList);
                }
                typeList.add(objectTypes[i]);
            }

            Set priorities = priorityMap.keySet();
            Iterator priority = priorities.iterator();
            while (priority.hasNext()) {
                List typeList = (List) priorityMap.get(priority.next());
                Iterator iterator = typeList.iterator();
                while (iterator.hasNext()) {
                    attemptedStarts++;
                    String objectType = (String) iterator.next();
                    ObjectType type = (ObjectType) ObjectType.getNamespace().valueOf(objectType);
                    Assertion.isValidReference(type, "Configured startup object type " + objectType
                            + " is not a defined Factory object.");
                    Object instance = factory.build(type);
                    Assertion.isValidReference(instance, "Configured startup object type " + objectType
                            + " is not a configured Factory object.");
                    Assertion.isTrue(instance instanceof Startable, "Configured startup object type " + objectType
                            + " of class " + instance.getClass() + " does not implement the Startable interface.");
                    Startable startUp = (Startable) instance;
                    if (startUp.startUp()) {
                        log.info("Class " + startUp.getClass().getName() + " initialized successfully.");
                        startedComponents.add(0, type);
                        successfulStarts++;
                    } else {
                        log.error("Class " + startUp.getClass().getName() + " reported initialization failure.");
                    }
                }
            }
            log.info("G5 System Initialization Sequence Ended. Attempted: " + attemptedStarts + " Successful: "
                    + successfulStarts);

        }
        return (attemptedStarts == successfulStarts);
    }

    /**
     * For every component that was sucessfully started by the startup() method,
     * obtain an instance from the object factory and call its shutDown()
     * method. Do this in the exact reverse order in which the components were
     * started.  Log the progress of the termination sequence.
     *
     * @return True if all configured components were shut down sucessfully,
     *         otherwise false.
     */
    public synchronized boolean shutDown() {
        int attemptedStops = 0;
        int successfulStops = 0;
        if (!startedComponents.isEmpty()) {
            Log log = getLog();
            log.info("G5 System Termination Sequence Started. System Environment = "
                    + getEnvironment().getExecutionEnvironment() + ".");
            ObjectFactory factory = getFactory();

            Iterator startUps = startedComponents.iterator();
            while (startUps.hasNext()) {
                attemptedStops++;
                ObjectType type = (ObjectType) startUps.next();
                Object instance = factory.build(type);
                Startable startUp = (Startable) instance;
                if (startUp.shutDown()) {
                    log.info("Class " + startUp.getClass().getName() + " terminated successfully.");
                    successfulStops++;
                } else {
                    log.error("Class " + startUp.getClass().getName() + " reported termination failure.");
                }
            }
            log.info("G5 System Termination Sequence Ended. Attempted: " + attemptedStops + " Successful: "
                    + successfulStops);
        }
        return (attemptedStops == successfulStops);
    }

    /**
     * Allows a test subclass the opportunity to inject a substitute collaborator.
     *
     * @return An instance of the SystemEnvironment class.
     */
    protected Environment getEnvironment() {
        return new SystemEnvironment();
    }

    /**
     * Allows a test subclass the opportunity to inject a substitute collaborator.
     *
     * @return An instance of the SystemLog class.
     */
    protected Log getLog() {
        return new SystemLog();
    }

    /**
     * Allows a test subclass the opportunity to inject a substitute collaborator.
     *
     * @return An instance of the ComponentConfiguration for this class.
     */
    protected Configuration getConfiguration() {
        return new ComponentConfiguration(this.getClass());
    }

    /**
     * Allows a test subclass the opportunity to inject a substitute collaborator.
     *
     * @return An instance of the Factory class.
     */
    protected ObjectFactory getFactory() {
        return new Factory();
    }

}