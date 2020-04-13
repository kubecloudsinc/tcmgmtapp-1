/**
 * 
 */
package com.onecloud.autotools.support.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enables assertions to be inserted into code to validate expected invariant
 * conditions. This class may not be instantiated - all methods are static.
 * 
 * @author sichituk
 *  
 */
public class Assertion {
	private static final Logger logger = LoggerFactory.getLogger(Assertion.class);

    /**
     * Private constructor ensures no instantiation.
     *  
     */
    private Assertion() {
        super();
    }

    /**
     * Asserts that a condition is true.
     * 
     * @param predicate
     *            The condition to test.
     * @param label
     *            If the assertion does not hold, this will be included in the
     *            Fault generated.
     */
    public static void isTrue(boolean predicate, String label) {
        if (!predicate) {
        	logger.error("Assertion failed: " + label);
            throw new Fault(label);
        }
    }

    /**
     * Asserts that a condition is false.
     * 
     * @param predicate
     *            The condition to test.
     * @param label
     *            If the assertion does not hold, this will be included in the
     *            Fault generated.
     */
    public static void isFalse(boolean predicate, String label) {
        isTrue(!predicate, label);
    }

    /**
     * Asserts that an object reference is null.
     * 
     * @param reference
     *            The reference to test.
     * @param label
     *            If the assertion does not hold, this will be included in the
     *            Fault generated.
     */
    public static void isNullReference(Object reference, String label) {
        isTrue((reference == null), label);
    }

    /**
     * Asserts that an object reference is non-null.
     * 
     * @param reference
     *            The reference to test.
     * @param label
     *            If the assertion does not hold, this will be included in the
     *            Fault generated.
     */
    public static void isValidReference(Object reference, String label) {
        isFalse((reference == null), label);
    }

}
