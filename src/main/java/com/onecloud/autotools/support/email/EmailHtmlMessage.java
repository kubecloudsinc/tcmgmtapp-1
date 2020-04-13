package com.onecloud.autotools.support.email;
/**
 * 
 * This is a subclass of {@link EmailMessage}. It indicates that the content of the email
 * message as a HTML text.
 * 
 * @author sichituk
 *
 */
public class EmailHtmlMessage extends EmailMessage {

	/**
	 * Setting content type of the email message as HTML text.
	 */
	private final String contentType = "text/html";
	
	public EmailHtmlMessage(String subject, String body, EmailDestination[] toDestinations,
			EmailDestination[] copyDestinations) {
		super(subject, body, toDestinations, copyDestinations);
		
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

}
