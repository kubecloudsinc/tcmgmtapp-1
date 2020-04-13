package com.onecloud.autotools.support.configuration;

import java.io.InputStream;

/**
 * An encapsulation of a versioned configuration file that resides on the
 * classpath. A configuration file may have more than one version to allow
 * parameter values to be tailored for different partner implementations or
 * execution environments. Not all configuration files will have multiple
 * versions - only those that are sensitive to different partner implementations
 * or execution environments.
 * <p>
 * The name of a configuration file identifies the class it configures and its
 * version. The formats of a configuration file name are:
 * <ul>
 * <li>baseName.ext - applies across all partners and execution environments.
 * <li>baseName[environment].ext - applies to a specific execution environment
 * across all partner implementations.
 * <li>baseName(partner).ext - applies to a specific partner implementation
 * across all execution environments.
 * </ul>
 * where baseName is default name of the file, (partner) specifies a particular
 * partner implementation version, [environment] specifies a particular
 * execution environment, and ext is the file extension. For example, the
 * configuration file for the System Log may have different versions for
 * different environments:
 * <ul>
 * <li>gov.g5.support.log.SystemLog[development].cfg
 * <li>gov.g5.support.log.SystemLog[ANI].cfg
 * <li>gov.g5.support.log.SystemLog[DEV].cfg
 * <li>gov.g5.support.log.SystemLog[UAT].cfg
 * <li>gov.g5.support.log.SystemLog[PROD].cfg
 * </ul>
 * <p>
 * In another example, the configuration files containing Email templates may
 * have different versions for different partners:
 * <ul>
 * <li>RefundReportConfirmation(ED).smtp
 * <li>RefundReportConfirmation(OJP).smtp
 * </ul>
 * 
 * <p>
 * File selection happens at runtime and depends on the settings encapsulated in
 * the SystemEnvironment class, which reflect settings of the application server
 * made through the management console. The open() method of this class attempts
 * to find versions of the base name and extension specified in the constructor
 * in this order, selecting the file name that matches first:
 * <ol>
 * <li>baseName[environment].ext
 * <li>baseName(partner).ext
 * <li>baseName.ext
 * </ol>
 * If none of the versions exist, no file is selected.
 * 
 * 
 * @author sichituk
 */
public class ConfigurationFile {
	private static Environment systemEnvironment = new SystemEnvironment();

	private String baseName = null;

	private String extension = null;

	private String fileName = null;

	/**
	 * Constructs an instance. defining the default file name of the
	 * configuration file.
	 * 
	 * @param baseName
	 *            The set of characters to the left of the last dot (case
	 *            sensitive) in the default file name.
	 * @param extension
	 *            The set of characters to the right of the last dot (case
	 *            sensitive) in the default file name.
	 */
	public ConfigurationFile(String baseName, String extension) {
		super();
		this.baseName = baseName;
		this.extension = extension;
		this.fileName = baseName + "." + extension;
	}

	/**
	 * Opens an input stream on the version of the configuration file specified
	 * by the system environment setting. File selection works this way: The
	 * open() method will first attempt to find a version of the configuration
	 * file that matches the current execution environment obtained from the
	 * SystemEnvironment class. If such a version exists, it is selected. If
	 * not, the open() method will attempt to find the default name - one that
	 * has no version designator. If the default file exists, it is selected.
	 * Otherwise, no file is selected.
	 * 
	 * @return An input stream on the version of the file that corresponds to
	 *         the execution environment setting, the partner setting, the
	 *         default version of the file, or null, if none of the versions
	 *         exist on the classpath.
	 */
	public InputStream open() {
		String environment = systemEnvironment.getExecutionEnvironment();
		String partner = systemEnvironment.getPartnerEnvironment();
		fileName = baseName + "[" + environment + "]." + extension;
		InputStream stream = openStream(fileName);
		if (stream == null) {
			fileName = baseName + "(" + partner + ")." + extension;
			stream = openStream(fileName);
			if (stream == null) {
				stream = openBaseFile();
			}
		}
		return stream;
	}

	/**
	 * Opens an input stream on the base version of the configuration file,
	 * without considering the system environment at all. This may be used to
	 * establish default settings carried in the base file.
	 * 
	 * @return An input stream on the base version of the file, or null, if it
	 *         does not exist on the classpath.
	 */
	public InputStream openBaseFile() {
		fileName = baseName + "." + extension;
		InputStream stream = openStream(fileName);
		return stream;
	}

	/**
	 * Opens a file for reading that is assumed to be on the classpath.
	 * 
	 * @param fileName
	 *            The name of the file to open. It must be on the classpath.
	 * @return An InputStream on the file, or null if the file can not be
	 *         located or opened.
	 */
	private InputStream openStream(String fileName) {
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		return stream;
	}

	/**
	 * Returns the file name that was most recently tried by the open() method.
	 * Or the default file name if the open() method was not invoked on the
	 * instance.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return fileName;
	}
}
