/**
 * 
 */
package com.onecloud.autotools.support.configuration;

import java.util.Map;
import java.util.Properties;

/**
 * An object that contains configuration information in the form of key-value
 * pairs.
 * 
 * @author sichituk
 */
public interface Configuration {
    /**
     * Obtains the String value of a configuration paremeter when supplied with a key.
     * 
     * @param key
     *        The key that identifies the configuration parameter value whose
     *        value is is sought.
     * @return The string value of the parameter, or null if the configuration
     *         does not contain a value for the parameter.
     */
    public String getStringValue(String key);

    /**
     * Obtains the integer value of a configuration paremeter when supplied with a key.
     * 
     * @param key
     *        The key that identifies the configuration parameter value whose
     *        value is is sought.
     * @return The integer value of the parameter.
     */
    public int getIntegerValue(String key);

    /**
     * Obtains the floating point value of a configuration paremeter when supplied with a key.
     * 
     * @param key
     *        The key that identifies the configuration parameter value whose
     *        value is is sought.
     * @return The floating point value of the parameter.
     */
    public float getFloatingPointValue(String key);

    /**
     * Obtains the boolean value of a configuration paremeter when supplied with a key.
     * 
     * @param key
     *        The key that identifies the configuration parameter value whose
     *        value is is sought.
     * @return The boolean value of the parameter.
     */
    public boolean getBooleanValue(String key);

    /**
     * Obtains a set of all keys in the component configuration.
     * 
     * @return A string-valued array of all keys found in the configuration.
     */
    public String[] getKeys();

    /**
     * Obtains a Map containing a set of compound configuration parameters that
     * share the same root key. Compound parameters have this syntax:
     * <ul>
     * <li> rootkey.name1 = value1 </li>
     * <li> rootkey.name2 = value2 </li>
     * <li> rootkey.name3 = value3 </li>
     * </ul>
     * 
     * @param rootKey
     *        The root key for the parameter set.
     * @return A Map whose keys correspond to name1, name2, etc. with
     *         corresponding String values value1, value2, etc. If rootKey is
     *         not found or if there are no compound parameters starting with
     *         rootKey, then the Map will be empty.
     */
    public Map getValueMap(String rootKey);
    
    /**
     * Obtains a copy of the underlying Properties object containing name/value
     * pairs that are part of the configuration settings.
     * 
     * @return A copy of the configuration in properties format.
     */
    public Properties getPropertiesObject();
}
