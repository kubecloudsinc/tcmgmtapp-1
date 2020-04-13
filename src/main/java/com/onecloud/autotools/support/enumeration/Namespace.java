package com.onecloud.autotools.support.enumeration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.onecloud.autotools.support.exception.Assertion;

/**
 * Defines a namespace for constant-valued objects that are intended to be
 * members of a Type-Safe Enumeration.  Any object type can be added to a namespace
 * provided that it implements the NamespaceElement interface, which requires it
 * to provide a string-valued key.  These keys must by unique within the namespace and
 * that is enforced when a new NamespaceElement is added.
 * <p>
 * The namespace concept allows Type-Safe enumerations to provide "valueOf()" functionality
 * which maps a string value to an enumeration member, and "values()" functionality which
 * obtains the entire set of defined constant values in the enumeration. 
 * 
 * @author sichituk
 *
 */
public class Namespace {

    private Map valueMap = null;

    /**
     *  Public constructor creates a namespace without any elements.
     */
    public Namespace() {
        this.valueMap = new HashMap();
    }

    /**
     * Private constructor allows the copy() method to instantiate a new object that
     * duplicates the original one.
     *  
     * @param original The original namespace object.
     */
    private Namespace(Namespace original) {
        this.valueMap = new HashMap();
        valueMap.putAll(original.valueMap);
    }

    /**
     * Adds an element to the Namespace and checks the uniqueness of its key.
     * 
     * @param element
     *        The element to add. It must have a key value that is distinct from
     *        all of the other elements currently in the namespace. It is
     *        considered a programming fault if an attempt is made to add 
     *        an element with a duplicate key.
     */
    public void addElement(NamespaceElement element) {
        String key = element.getKey();
        Assertion.isFalse(valueMap.containsKey(key), "Duplicate key " + key + " detected in namespace.");
        valueMap.put(key, element);
    }

    /**
     * Obtains the namespace object that corresponds to the supplied key.
     * 
     * @param key
     *        Specifies the string for the desired namespace object.
     * @return The constant object corresponding to the key, or null if there is
     *         no constant object that matches the key.
     */
    public NamespaceElement valueOf(String key) {
        return (NamespaceElement) valueMap.get(key);
    }

    /**
     * Obtains an array containing all of the defined elements in the namespace.
     * 
     * @return The array.
     */
    public NamespaceElement[] values() {
        Collection values = valueMap.values();
        NamespaceElement[] valueArray = new NamespaceElement[values.size()];
        int index = 0;
        Iterator i = values.iterator();
        while (i.hasNext()) {
            valueArray[index++] = (NamespaceElement) i.next();
        }
        return valueArray;
    }

    /**
     * Obtains an exact copy of the Namespace object
     * 
     * @return A new Namespace, whose values are identical to the original
     *         namespace.
     */
    public Namespace copy() {
        return new Namespace(this);
    }

}
