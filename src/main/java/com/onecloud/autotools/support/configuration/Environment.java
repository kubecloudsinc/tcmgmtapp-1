package com.onecloud.autotools.support.configuration;

/**
 * Encapsulates the identity of the execution environment. The identity is set
 * on the server via environment variable setting. The setting of the execution
 * environment determines which versions of configuration files used to govern
 * the behavior of the system in that environment.
 * <p>
 * The setting of the execution environment on the server can be used to
 * differentiate between local, development test, system test, and production
 * incarnations of the system. Addtionally, the execution environment can be
 * used to differentiate consortia partner incarnations of the system.
 * 
 * @author sichituk
 * 
 */
public interface Environment {
	/**
	 * Obtains the execution environment setting for the
	 * gov.g5.execution.environment variable on the JVM. The default environment -
	 * development - is used when running the system on the developer's local
	 * workstation for development and testing purposes. When there is no server
	 * setting for the gov.g5.execution.environment variable, the default
	 * environment is assumed.
	 * 
	 * @return The designated execution environment or "development" if none was set
	 *         on the server.
	 */
	public String getExecutionEnvironment();
	
	/**
	 * Obtains the partner setting for the
	 * gov.g5.partner.environment variable on the JVM. The default partner -
	 * partner - is used when running the system on the developer's local
	 * workstation for development and testing purposes. When there is no server
	 * setting for the gov.g5.execution.environment variable, the default
	 * environment is assumed.
	 * 
	 * @return The designated execution environment or "partner" if none was set
	 *         on the server.
	 */
	public String getPartnerEnvironment();
}
