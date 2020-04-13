package com.onecloud.autotools.support.configuration;

import com.onecloud.autotools.support.enumeration.Namespace;
import com.onecloud.autotools.support.enumeration.NamespaceElement;

/**
 * An enumeration of the objects that the Factory is able to build.
 * The values correspond to bean definitions in the configuration file.
 */
public class ObjectType implements NamespaceElement {
    /**
     * The namespace for the eumeration.
     */
    private static Namespace namespace = new Namespace();

    /**
     * The unique string value corresponding to each constant member of the
     * enumeration.
     */
    private String key = null;

    /**
     * Obtains a copy of the namespace object for the enumeration. This allows
     * access to the complete set of constants defined in the enumeration.
     * 
     * @return A copy of the enumeration's namespace.
     */
    public static Namespace getNamespace() {
        return namespace.copy();
    }

    /**
     * Private constructor for constant members of the enumeration.
     * 
     * @param key
     *        The unique key value to associate with the constant member.
     */
    private ObjectType(String key) {
        this.key = key;
        namespace.addElement(this);
    }

    /*
     * @see gov.g5.support.enumeration.NamespaceElement#getKey()
     */
    public String getKey() {
        return this.key;
    }
    /**
     * Obtains the key value corresponding to a constant object.
     * 
     * @return Its key value.
     */
    public String toString() {
        return getKey();
    }
    
    ///////////////////////////////////
    // Start of Constant Definitions //
    ///////////////////////////////////
    
    public static final ObjectType CONFIGURATION = new ObjectType("configuration");
    public static final ObjectType ENVIRONMENT = new ObjectType("environment");
    
    //Removed LOG definition to satisfy possible portlet unavailable when trying to instantiate a SystemLog
    //object using ObjectFactory.  Should now use 	return new SystemLog();
    //public static final ObjectType LOG = new ObjectType("log");

}