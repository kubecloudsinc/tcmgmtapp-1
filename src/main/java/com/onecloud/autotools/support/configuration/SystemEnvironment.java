package com.onecloud.autotools.support.configuration;

/**
 * Specifies the environment in which the application is to run, which
 * determines the set of configuration files that are selected at run time. The
 * setting of the system environment determines which versions of configuration
 * files used to govern the behavior of the system. The system environment
 * comprises two complementary settings: Execution Environment and Partner
 * Environment.
 * <p>
 * To set the value on the application server use the management console and
 * click through to Application servers > WebSphere_Portal > Process Definition >
 * Java Virtual Machine > Custom Properties and create or edit the values for the
 * gov.g5.execution.environment and gov.g5.partner.environment variables.
 * <p>
 * The default execution environment - development - is used when running the system on
 * the developer's local workstation for development and testing purposes. When
 * there is no server setting for the gov.g5.execution.environment variable, the
 * default environment is assumed.
 * <p>
 * The default partner environment - partner - is used when running the system on
 * the developer's local workstation for development and testing purposes. When
 * there is no server setting for the gov.g5.partner.environment variable, the
 * default environment is assumed.
 * 
 * @author sichituk
 */
public class SystemEnvironment implements Environment {
	private static final String EXECUTION_ENVIRONMENT_VARIABLE_NAME = "com.cisco.cstg.autotools.environment";

	private static final String PARTNER_ENVIRONMENT_VARIABLE_NAME = "com.cisco.cstg.autotools.partner.environment";

	private static final String DEFAULT_EXECUTION_ENVIRONMENT = "development";

	private static final String DEFAULT_PARTNER_ENVIRONMENT = "partner";

	/*
	 * @see gov.g5.support.configuration.Environment#getExecutionEnvironment()
	 */
	public String getExecutionEnvironment() {
		String designator = System.getProperty(EXECUTION_ENVIRONMENT_VARIABLE_NAME);
		if (designator == null) {
			designator = DEFAULT_EXECUTION_ENVIRONMENT;
		}
		return designator;
	}

	/*
	 * @see gov.g5.support.configuration.Environment#getPartnerEnvironment()
	 */
	public String getPartnerEnvironment() {
		String designator = System.getProperty(PARTNER_ENVIRONMENT_VARIABLE_NAME);
		if (designator == null) {
			designator = DEFAULT_PARTNER_ENVIRONMENT;
		}
		return designator;
	}

}
