/**
 * 
 */
package com.onecloud.autotools.semantic.diagnostics;

import com.onecloud.autotools.support.exception.Assertion;
import com.onecloud.autotools.support.exception.Contingency;

/**
 * Thrown to indicate that one or more parameters that were subject to
 * validation failed to validate properly. This exception contains a list of
 * diagnostics that the throwing method optionally includes. These diagnostics
 * allow the throwing method to include a distinct diagnostic object for every
 * parameter that failed the validation.
 * 
 * @author sichituk
 */
public class ValidationException extends Contingency {
    /**
     * 
     */
    private static final long serialVersionUID = 379752432536285603L;
    private Diagnostic[] diagnostics = null;

    /**
     * Creates an instance that contains no diagnostic information.
     */
    public ValidationException() {
        this(new Diagnostic[0]);
    }

    /**
     * Creates an instance that incldes a list of Diagnostic objects.
     * 
     * @param diagnostics
     *        A non-null array of Diagnostic objects to include in the exception
     *        object.
     */
    public ValidationException(Diagnostic[] diagnostics) {
        super();
        Assertion.isValidReference(diagnostics, "Diagnostics must be a valid array reference.");
        this.diagnostics = diagnostics;
    }

    /**
     * @return the diagnostics
     */
    public Diagnostic[] getDiagnostics() {
        return diagnostics;
    }

}
