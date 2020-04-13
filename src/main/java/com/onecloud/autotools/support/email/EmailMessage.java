package com.onecloud.autotools.support.email;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.onecloud.autotools.support.configuration.ComponentConfiguration;
import com.onecloud.autotools.support.configuration.Configuration;

/**
 * A completely elaborated Email message, containing a subject, a body, "to"
 * recipients and "cc" recipients.  The semantic tier assembles EmailMessage objects
 * and submits them for transmission through the PostOffice interface.
 * 
 * @author sichituk
 * 
 */
public class EmailMessage {
	
	private static final Configuration config = new ComponentConfiguration(EmailMessage.class);
	
	private String subject = null;

	private String body = null;

	private EmailDestination[] toDestinations = null;

	private EmailDestination[] copyDestinations = null;
	
	private EmailDestination fromDestination = null;
	
	
	/**
	 * The content type of the email
	 */
	private static final String TEXT_PLAIN = "text/plain";
	
	private static final String DEFAULT_FROM_ADDRESS = "default.FromAddress";
	private static final String DEFAULT_TO_ADDRESS = "default.ToAddress";
	
	
	/**
	 * Setting content type of the email message as plain text.
	 */
	private String contentType = TEXT_PLAIN;
	
	/**
	 * Denotes whether the message is multipart
	 */
	private boolean multipart = false;
	
	/**
	 * Holds the attachments file names
	 */
	private EmailAttachment[] attachments;
	
	public EmailMessage(){
		try {
			this.fromDestination = new EmailDestination(new InternetAddress(config.getStringValue(DEFAULT_FROM_ADDRESS)));
			this.toDestinations = new EmailDestination [] {new EmailDestination(new InternetAddress(config.getStringValue(DEFAULT_TO_ADDRESS)))};
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Creates an Email message object, with attachments ready for sending.
	 * 
	 * @param subject
	 *            The subject line of the message, as it should appear to the recipients.
	 * @param body
	 *            The body text of the message, as it should appear to the recipients.
	 * @param toDestinations
	 *            An array of destination email Internet addresses for the "To"
	 *            field.
	 * @param copyDestinations
	 *            An array of destination email Internet addresses for the "Cc"
	 *            field.
	 * @param contentType
	 * 			  An array of EmailAttachments that hold the attachment file names.
	 */
	public EmailMessage(String subject, String body, EmailDestination[] toDestinations,
			EmailDestination[] copyDestinations, String contentType) {
		this.subject = subject;
		this.body = body;
		this.toDestinations = toDestinations;
		this.copyDestinations = copyDestinations;

		this.contentType = contentType;
		// this needs to create a multipart message.
		this.multipart = true;
	}
	
	/**
	 * Creates an Email message object, ready for sending.
	 * 
	 * @param subject
	 *            The subject line of the message, as it should appear to the recipients.
	 * @param body
	 *            The body text of the message, as it should appear to the recipients.
	 * @param toDestinations
	 *            An array of destination email Internet addresses for the "To"
	 *            field.
	 * @param copyDestinations
	 *            An array of destination email Internet addresses for the "Cc"
	 *            field.
	 */
	public EmailMessage(String subject, String body, EmailDestination[] toDestinations,
			EmailDestination[] copyDestinations) {
		super();
		this.subject = subject;
		this.body = body;
		this.toDestinations = toDestinations;
		this.copyDestinations = copyDestinations;
	}
	
	/**
	 * Creates an Email message object, ready for sending.
	 * 
	 * @param subject
	 *            The subject line of the message, as it should appear to the recipients.
	 * @param body
	 *            The body text of the message, as it should appear to the recipients.
	 * @param fromDestination
	 * 			  A from destination email Internaet address for the "From" field.
	 * @param toDestinations
	 *            An array of destination email Internet addresses for the "To"
	 *            field.
	 * @param copyDestinations
	 *            An array of destination email Internet addresses for the "Cc"
	 *            field.
	 */
	public EmailMessage(String subject, String body, EmailDestination fromDestination, EmailDestination[] toDestinations,
			EmailDestination[] copyDestinations) {
		super();
		this.subject = subject;
		this.body = body;
		this.fromDestination = fromDestination;
		this.toDestinations = toDestinations;
		this.copyDestinations = copyDestinations;
	}

	/**
	 * Obtains the body of the email message.
	 * 
	 * @return The body of the email message.
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Obtains the set of CC addresses.
	 * 
	 * @return The set of InternetAddress objects to which the message will be
	 *         sent as "CC" recipients.
	 */
	public InternetAddress[] getCopyAddresses() {
		InternetAddress[] addresses = new InternetAddress[this.copyDestinations.length];
		for (int i = 0; i < addresses.length; i++) {
			addresses[i] = this.copyDestinations[i].getAddress();
		}
		return addresses;
	}

	/**
	 * Obtains the subject line.
	 * 
	 * @return The subject line of the email message
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Obtains the set of TO addresses.
	 * 
	 * @return The set of InternetAddress objects to which the message will be
	 *         sent as "TO" recipients.
	 */
	public InternetAddress[] getToAddresses() {
		if (this.toDestinations == null || this.toDestinations.length ==0) {
			try {
				this.toDestinations = new EmailDestination [] {new EmailDestination(new InternetAddress(config.getStringValue(DEFAULT_TO_ADDRESS)))};
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		InternetAddress[] addresses = new InternetAddress[this.toDestinations.length];
		for (int i = 0; i < addresses.length; i++) {
			addresses[i] = this.toDestinations[i].getAddress();
		}
		return addresses;
	}
	
	/**
	 * Obtains the FROM address.
	 * 
	 * @return A InternetAddress objects to which the message will be
	 *         from the "FROM" recipients.
	 */
	public InternetAddress getFromAddress() {

		if (this.fromDestination == null) {
			try {
				this.fromDestination = new EmailDestination(new InternetAddress(config.getStringValue(DEFAULT_FROM_ADDRESS)));
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		InternetAddress address = new InternetAddress();
		address = this.fromDestination.getAddress();
	
		return address;
	}

	/**
	 * Generates a formatted representation of the Email, including addresses,
	 * subject line, and body, suitable for logging.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		InternetAddress[] addresses = null;
		StringBuffer buffer = new StringBuffer();
	
		buffer.append("From: " + ((getFromAddress() == null) ? "":getFromAddress().getAddress()) + "\n");
		
		buffer.append("To:\n");
		addresses = getToAddresses();
		for (int i = 0; i < addresses.length; i++) {
			buffer.append(addresses[i] + "\n");
		}
		buffer.append("Cc:\n");
		addresses = getCopyAddresses();
		for (int i = 0; i < addresses.length; i++) {
			buffer.append(addresses[i] + "\n");
		}
		buffer.append("Subject:\n");
		buffer.append(getSubject() + "\n");
		buffer.append("OCIMessage:\n");
		buffer.append(getBody() + "\n");
		return buffer.toString();
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @return the attachments
	 */
	public EmailAttachment[] getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(EmailAttachment[] attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the multipart
	 */
	public boolean isMultipart() {
		return multipart;
	}

	/**
	 * @param multipart the multipart to set
	 */
	public void setMultipart(boolean multipart) {
		this.multipart = multipart;
	}
}
