package com.onecloud.autotools.support.configuration;

import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onecloud.autotools.support.exception.Assertion;
import com.onecloud.autotools.support.exception.Fault;

/**
 * Respresents the static or dynamic configuration of a component in the system.
 * Static configurations take the form of properties files, that this class
 * loads and interprets based on a naming convention based on class name.
 * Configuration settings are cached at the class level so that creating
 * multiple instances of this class for a particular component class will result
 * in only a single set of configuration settings existing in the system.
 * <p>
 * Dynamic configuration takes the form of a Properties object, injected into an
 * instance of this class during construction. Dynamically configurable
 * components must provide a way to inject a new instance of this class.
 * 
 * @author sichituk
 */
public class ComponentConfiguration implements Configuration {
	
	private static final Logger log = LoggerFactory.getLogger(ComponentConfiguration.class);
	/**
	 * Caches one copy of each component configuration data indexed by component
	 * Class.
	 */
	private static final Map configMap = Collections.synchronizedMap(new HashMap());

	/**
	 * Configuration data selected depends on the system environment settings.
	 */
	private static final Environment environment = new SystemEnvironment();

	/**
	 * The copy of the component's configuration data for this instance.
	 */
	private Properties configSettings = null;

	/**
	 * The component whose configuration is represented in this object.
	 */
	private Class componentClass = null;

	/**
	 * Creates a ComponentConfiguration object containing the settings for a
	 * specified component. This constructor will look for a configuration file
	 * in the class path whose name corresponds to the componentClass object
	 * supplied.
	 * <p>
	 * A component's configuration file may have more than one version to allow
	 * parameter values to be tailored for different execution environments. Not
	 * all configuration files will have multiple versions - only those that are
	 * sensitive to different execution environments.
	 * <p>
	 * The name of a configuration file identifies the class it configures and
	 * its version. The format of a configuration file name is:
	 * <p>
	 * className[-versionName].cfg
	 * <p>
	 * where className is the fully qualified class name of the class being
	 * configured and versionName is an optional version designator. Thus, the
	 * configuration file for a class:
	 * <p>
	 * gov.g5.subsystem.MyComponent
	 * <p>
	 * could have these versions:
	 * <ul>
	 * <li>gov.g5.subsystem.MyComponent.cfg
	 * <li>gov.g5.subsystem.MyComponent[DEV].cfg
	 * <li>gov.g5.subsystem.MyComponent[ANI].cfg
	 * <li>gov.g5.subsystem.MyComponent[PROD].cfg
	 * </ul>
	 * <p>
	 * File selection works this way: The constructor will first attempt to find
	 * a version of the configuration file that matches the current execution
	 * environment obtained from the SystemEnvironment class. If such a version
	 * exists, it is selected. If not, the constructor will attempt to find a
	 * file that has no versionName. If one exists, it is selected. If no file
	 * exists corresponding to the component class, it is considered to be a
	 * programming fault.
	 * <p>
	 * The constructor caches configuration settings by component, so that
	 * multiple ComponentConfiguration objects for the same component share the
	 * same data.
	 * 
	 * @param componentClass
	 *            Identifes the component whose configuration the constructed
	 *            object will contain.
	 */
	public ComponentConfiguration(Class componentClass) {
		
		
		log.debug("Inside Config class");
		Assertion.isValidReference(componentClass, "Null parameter is not valid.");
		Properties configSettings = (Properties) configMap.get(componentClass);
		if (configSettings == null) {
			configSettings = new Properties();
			ConfigurationFile configFile = new ConfigurationFile(componentClass.getName(), "cfg");
			log.debug("Inside Component Configuration: "+ configFile.toString()+" "+Thread.currentThread().getContextClassLoader().getResource(configFile.toString()).getPath());
			InputStream stream = configFile.open();
			Assertion.isValidReference(stream, "No configuration file was found for class " + componentClass);
			try {
				configSettings.load(stream);
			} catch (Exception e) {
				throw new Fault("Unable to load configuration data for class " + componentClass + " from file "
						+ configFile, e);
			}
			configMap.put(componentClass, configSettings);
			this.componentClass = componentClass;
			this.configSettings = configSettings;
			log.info("Configuration for class " + componentClass.getName() + " loaded from file " + configFile + "\n"
					+ this);
		} else {
			this.componentClass = componentClass;
			this.configSettings = configSettings;
		}

	}

	/**
	 * Allows a subclass to substitute an Environment for unit test purposes.
	 * 
	 * @return Default return is the static SystemEnvironment object held by
	 *         this class.
	 */
	protected Environment getEnvironment() {
		return ComponentConfiguration.environment;
	}

	/**
	 * Special constructor for dynamic configuration, allowing the direct
	 * injection of configuration settings into a ComponentConfiguration object.
	 * This capability can be used for unit testing, or to dynamically configure
	 * components.
	 * 
	 * @param configSettings
	 *            Contains a set of configuration settings to be used.
	 */
	public ComponentConfiguration(Class componentClass, Properties configSettings) {
		Assertion.isValidReference(configSettings, "Null parameter is not valid.");
		this.componentClass = componentClass;
		this.configSettings = configSettings;
	}

	/*
	 * @see gov.g5.support.configuration.Configuration#getStringValue(java.lang.String)
	 */
	public String getStringValue(String key) {
		Assertion.isValidReference(key, "Null parameter is not valid.");
		return configSettings.getProperty(key);
	}

	/*
	 * @see gov.g5.support.configuration.Configuration#getBooleanValue(java.lang.String)
	 */
	public boolean getBooleanValue(String key) {
		Assertion.isValidReference(key, "Null parameter is not valid.");
		return Boolean.valueOf(configSettings.getProperty(key)).booleanValue();
	}

	/*
	 * @see gov.g5.support.configuration.Configuration#getFloatingPointValue(java.lang.String)
	 */
	public float getFloatingPointValue(String key) {
		Assertion.isValidReference(key, "Null parameter is not valid.");
		return Float.valueOf(configSettings.getProperty(key)).floatValue();
	}

	/*
	 * @see gov.g5.support.configuration.Configuration#getIntegerValue(java.lang.String)
	 */
	public int getIntegerValue(String key) {
		Assertion.isValidReference(key, "Null parameter is not valid.");
		return Integer.valueOf(configSettings.getProperty(key)).intValue();
	}

	/*
	 * @see gov.g5.support.configuration.Configuration#getKeys()
	 */
	public String[] getKeys() {
		Set keys = configSettings.keySet();
		return (String[]) keys.toArray(new String[keys.size()]);
	}

	/*
	 * @see gov.g5.support.configuration.Configuration#getValueMap(java.lang.String)
	 */
	public Map getValueMap(String rootKey) {
		Assertion.isValidReference(rootKey, "Null parameter is not valid.");
		Map valueMap = new HashMap();
		Enumeration names = configSettings.propertyNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String[] parts = name.split("\\.");
			if ((parts.length == 2) && (parts[0].equals(rootKey))) {
				String mapKey = parts[1];
				String mapValue = configSettings.getProperty(name);
				valueMap.put(mapKey, mapValue);
			}
		}
		return valueMap;
	}

	/**
	 * Produces a formatted report of the names and values of all parameters in
	 * the configuration object.
	 * 
	 * @return The formatted report.
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Configuration settings for class " + componentClass.getName() + ":");
		Set names = new TreeSet(configSettings.keySet());
		Iterator iterator = names.iterator();
		while (iterator.hasNext()) {
			String name = (String) iterator.next();
			String value = configSettings.getProperty(name);
			buffer.append("\n\t[] " + name + " = " + value);
		}
		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see gov.g5.support.configuration.Configuration#getPropertiesObject()
	 */
	public Properties getPropertiesObject() {
		Properties copy = new Properties();
		copy.putAll(configSettings);
		return copy;
	}
	

}