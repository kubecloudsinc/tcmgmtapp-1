package com.onecloud.autotools.support.configuration;

import com.onecloud.autotools.support.exception.Assertion;


import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.InputStreamResource;

/**
 * A factory that produces objects specified by configuration, promoting the
 * ability to substitute implementations by using alternate versions of
 * the configuration information. The factory uses the naming and selection
 * conventions established by the ConfigurationFile class to locate its own
 * configuration information. The default name of the configuration file is
 * "factory.xml", although the implementation will look for a version of that
 * file that matches the system execution environment, such as
 * "factory-local.xml", "factory-uat.xml", "factory-partner.xml", etc.
 * <p>
 * The format of the configuration file follows the rules for an XML-based
 * BeanFactory in the Spring DI framework, which underlies this implementation.
 * <p>
 * To define a class to the factory, two steps are needed. First, a <bean>
 * element must be added to the configuration file that specifies the
 * implementation class that the factory should build when supplied with an ID.
 * Second, a constant must be added to the ObjectType enumeration that
 * represents the ID attribute of the <bean> element. ObjectType constants thus
 * map to concrete class names in the configuration file.
 * 
 * @author sichituk
 */
public class Factory implements ObjectFactory {
    private static final String FACTORY_CONFIGURATION_BASE_NAME = "factory";

    private static final String FACTORY_CONFIGURATION_EXTENSION = "xml";

    private static BeanFactory beanFactory = null;

    private static final Logger log = LoggerFactory.getLogger(Factory.class);

    static {
        ConfigurationFile configFile = new ConfigurationFile(FACTORY_CONFIGURATION_BASE_NAME,
                FACTORY_CONFIGURATION_EXTENSION);
        InputStream configStream = configFile.open();
        Assertion.isValidReference(configStream, "Can not open the Factory configuration file named " + configFile);
        InputStreamResource resource = new InputStreamResource(configStream);
        beanFactory = new XmlBeanFactory(resource);
        log.debug("Object factory configuration successfully loaded from " + configFile);
    }

    /**
     * Builds an object of the specified type, according to the factory
     * configuration.
     * 
     * @see gov.g5.support.configuration.ObjectFactory#build(gov.g5.support.configuration.ObjectType)
     */
    public Object build(ObjectType type) {
        String objectID = type.getKey();
        Object object = beanFactory.getBean(objectID);
        log.debug("Built an instance of class: " + object.getClass().getName() + " for requested ObjectType: " + type);
        return object;
    }
    
    /**
     * Builds an object of the bean id, as defined in the factory configuration
     * @param beanId Bean Id as defined in factory configuration file factory.xml
     * @return an instance of bean, or null if the bean id does not exist in configuration file.
     * 
     * @see gov.g5.support.configuration.ObjectFactory#build(String)
     */
    public Object build(String beanId) {
        if(StringUtils.isBlank(beanId))
        {
        	log.error("Argument is null. returning null");
        	return null;
        }
        
        Object object = beanFactory.getBean(beanId);
        if(object == null)
        {
        	log.error("Unable to get instance of bean with Id: " + beanId);
        }else
        {
        	log.debug("Built an instance of class: " + object.getClass().getName() + " for requested beanId: " + beanId);
        }
        
        return object;
    }

}
