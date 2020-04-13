/**
 * 
 */
package com.onecloud.autotools.semantic.diagnostics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Encapsulates the details of an informational, warning, or error condition,
 * including an indication of the condition, and a list of detail objects that
 * may be used to convey additional information about the condition.
 * 
 * @author sichituk
 */
public class Diagnostic {
    private ConditionCode code = null;

    private List details = new ArrayList();
    
    private String componentId = null;

    /**
     * @return the componentId
     */
    public String getComponentId() {
        return componentId;
    }

    /**
     * @param componentId the componentId to set
     */
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    /**
     * Creates a diagnostic from a condition code and a list of detail objects.
     * 
     * @param code
     *        The condition that the diagnostic is expressing.
     * @param details
     *        A list of objects that supply details pertaining to the condition.
     */
    public Diagnostic(ConditionCode code, List details) {
        super();
        this.code = code;
        this.details.addAll(details);
    }

    /**
     * Creates a diagnostic from a condition code and a single detail object.
     * 
     * @param code
     *        The condition that the diagnostic is expressing.
     * @param detail
     *        An object that will be the only member of the detail list of the
     *        diagnostic.
     */
    public Diagnostic(ConditionCode code, Object detail) {
        super();
        this.code = code;
        this.details.add(detail);
    }

    
    /**
    * Creates a diagnostic from a condition code and a single detail object.
    * 
    * @param code
    *        The condition that the diagnostic is expressing.
    * @param detail
    *        An object that will be the only member of the detail list of the
    *        diagnostic.
    * @param componentId
            The id of the component this message belongs to
    */
   public Diagnostic(ConditionCode code, Object detail, String componentId) {
       super();
       this.code = code;
       this.details.add(detail);
       this.componentId = componentId;
   }
    /**
     * Creates a diagnostic from a condition code, without any details.
     * 
     * @param code
     *        The condition that the diagnostic is expressing.
     */
    public Diagnostic(ConditionCode code) {
        super();
        this.code = code;
    }

    /**
     * Obtains the condition code.
     * 
     * @return The condition code.
     */
    public ConditionCode getCode() {
        return code;
    }

    /**
     * Obtains the detail list - the list may have zero, one, or more than one
     * elements in it, depending on how the diagnostic was constructed.
     * 
     * @return The detail list.
     */
    public List getDetails() {
        return details;
    }

    /**
     * String representation of the Diagnostic: "code[,detail1,...,detailN]".
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(getCode().toString());
        Iterator i = getDetails().iterator();
        while (i.hasNext()) {
            result.append(",");
            result.append(i.next().toString());
        }
        return result.toString();
    }
}
