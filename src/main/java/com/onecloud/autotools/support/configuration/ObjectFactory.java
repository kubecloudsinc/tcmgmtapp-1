package com.onecloud.autotools.support.configuration;

/**
 * A factory that produces objects specified by configuration, promoting the
 * ability to substitute implementations by using alternate versions of
 * the configuration information.
 * 
 * @author sichituk
 */
public interface ObjectFactory {
    /**
     * Builds an object of the requested type, as defined in the factory
     * configuration.
     * 
     * @param type
     *        The type of object to build.
     * @return An instance of the requested object - the actual type of the
     *         object is determined by the factory configuration.
     */
    public Object build(ObjectType type);
    
    
    /**
     * Builds an object of the bean id, as defined in the factory configuration
     * @param beanId Bean Id as defined in factory configuration file factory.xml
     * @return an instance of bean, or null if the bean id does not exist in configuration file.
     */
    public Object build(String beanId);
}
