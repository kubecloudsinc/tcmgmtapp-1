package com.onecloud.autotools.support.email;

import java.io.File;

import javax.activation.FileDataSource;

/**
 * Specifies an email attachment.
 * An email attachment must have some reference to a file or an 
 * object and a type.If no type is specified, then the type is looked up 
 * by the filename given.
 * @author sichituk
 * @version October 20, 2009 
 */
public class EmailAttachment {
	
	/**
	 * The file data source
	 */
	private FileDataSource datasource;
	
	/**
	 * The short filename, usually the actual filename of the file without the path
	 */
	private String shortFilename;
	
	/**
	 * Constructor for initializing the attachment with a file
	 * @param filename	The full filename of the file
	 */
	public EmailAttachment(String filename) {
		this(new File(filename));
	}
	
	/**
	 * Constructor for initializing the attachment with a file
	 * @param file The file object
	 */
	public EmailAttachment(File file) {
		datasource = new FileDataSource(file);
		shortFilename = file.getName();
		datasource.setFileTypeMap(new AttachmentTypeMap());
	}

	/**
	 * @return the datasource
	 */
	public FileDataSource getDatasource() {
		return datasource;
	}

	/**
	 * @param datasource the datasource to set
	 */
	public void setDatasource(FileDataSource datasource) {
		this.datasource = datasource;
	}

	/**
	 * @return the shortFilename
	 */
	public String getShortFilename() {
		return shortFilename;
	}

	/**
	 * @param shortFilename the shortFilename to set
	 */
	public void setShortFilename(String shortFilename) {
		this.shortFilename = shortFilename;
	}
}

