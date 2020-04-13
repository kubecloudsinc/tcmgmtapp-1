package com.onecloud.autotools.support.email;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.activation.FileTypeMap;

/**
 * This is an enumeration class to denote attachment types.
 * Attachment types specify the content-type for a specific file.
 * @author sichituk
 */
public final class AttachmentTypeMap extends FileTypeMap {

	/**
	 * Specifies a text file
	 */
	public static final AttachmentTypeMap TEXT_FILE = new AttachmentTypeMap("text/plain");

	/**
	 * Specifies a binary file
	 */
	public static final AttachmentTypeMap BINARY_FILE = new AttachmentTypeMap("application/octet-stream");

	/**
	 * Specifies a PDF file
	 */
	public static final AttachmentTypeMap PDF_FILE = new AttachmentTypeMap("application/pdf");

	/**
	 * Specifies an RTF file
	 */
	public static final AttachmentTypeMap RTF_FILE = new AttachmentTypeMap("application/rtf");

	/**
	 * Specifies an Powerpoint file
	 */
	public static final AttachmentTypeMap PPT_FILE = new AttachmentTypeMap("application/vnd.ms-powerpoint");
	
	/**
	 * Specifies an MS-Word file
	 */
	public static final AttachmentTypeMap DOC_FILE = new AttachmentTypeMap("application/msword");

	/**
	 * The content type
	 */
	private String contentType;
	
	/**
	 * The content type map
	 */
	private static Map contentTypeMap;
	

	/**
	 * Re
	 * @param contentType the content type for this attachment type
	 * 
	 */
	private AttachmentTypeMap(String contentType) {
		setContentType(contentType);
	}
	

	/**
	 * Re
	 * @param contentType the content type for this attachment type
	 * 
	 */
	public AttachmentTypeMap() {
	}
	
	/**
	 * Initializes the content type map
	 */
	private static void initializeMap() {
		contentTypeMap = new HashMap();
		contentTypeMap.put(".txt", AttachmentTypeMap.TEXT_FILE); 
		contentTypeMap.put(".xml", AttachmentTypeMap.TEXT_FILE); 
		contentTypeMap.put(".bin", AttachmentTypeMap.BINARY_FILE); 
		contentTypeMap.put(".doc", AttachmentTypeMap.DOC_FILE); 
		contentTypeMap.put(".rtf", AttachmentTypeMap.RTF_FILE); 
		contentTypeMap.put(".ppt", AttachmentTypeMap.PPT_FILE); 
		contentTypeMap.put(".pdf", AttachmentTypeMap.PDF_FILE); 
	}
	
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	private void setContentType(String contentType) {
		this.contentType = contentType;
	}



	/**
	 * Returns the content type for a given file
	 * @return the content-type for a given file
	 */
	public String getContentType(File file) {
		if (contentTypeMap == null) {
			initializeMap();
		}
		String arg0 = file.getName();
		int lastPeriod = arg0.lastIndexOf('.');
		AttachmentTypeMap returnVal = BINARY_FILE;
		if (lastPeriod > 0 && lastPeriod < arg0.length() - 1) {
			String extension = arg0.substring(lastPeriod);
			returnVal = (AttachmentTypeMap)contentTypeMap.get(extension);
			if (returnVal == null) {
				returnVal = BINARY_FILE;
			}
		}
		return returnVal.getContentType();
	}

	/**
	 * Returns the content type for a given file
	 * @return the content-type for a given file
	 */
	public String getContentType(String arg0) {
		if (contentTypeMap == null) {
			initializeMap();
		}
		int lastPeriod = arg0.lastIndexOf('.');
		AttachmentTypeMap returnVal = BINARY_FILE;
		if (lastPeriod > 0 && lastPeriod < arg0.length() - 1) {
			String extension = arg0.substring(lastPeriod);
			returnVal = (AttachmentTypeMap)contentTypeMap.get(extension);
			if (returnVal == null) {
				returnVal = BINARY_FILE;
			}
		}
		return returnVal.getContentType();
	}
}
