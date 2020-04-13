package com.onecloud.autotools.support.email;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.onecloud.autotools.support.configuration.ComponentConfiguration;
import com.onecloud.autotools.support.configuration.Configuration;
import com.onecloud.autotools.support.exception.Assertion;
import com.onecloud.autotools.support.initialization.Startable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serves as the interface to an external SMTP server for the sending of Email
 * messages, deferring their transmission until commit time. This class is a
 * WorkUnit, allowing it to participate in the transactional WorkContext of a
 * process.
 * 
 * @author sichituk
 * 
 */
public class EmailSender implements Startable {
	
	private static final Configuration config = new ComponentConfiguration(EmailSender.class);

	private static final Logger log = LoggerFactory.getLogger(EmailSender.class);

	private static final String MAIL_SERVER = "mail.server";
	//
	// private static final String MAIL_PORT = "mail.server.port";
	//
	// private static final String MAIL_ACCOUNT = "mail.server.account";
	//
	// private static final String MAIL_PASSWORD = "mail.server.password";

	private static final String DEBUG = "debug.trace";
	
	public static final String DEFAULT_FROM_ADDRESS = "default.FromAddress";

	private static Object jndiSessionObject = null;

	private Session session = null;

	private List messages = new ArrayList();
	
	private static final String UTF_8 = "UTF-8";

	/**
	 * Called when the system is terminating. Releases the JNDI session
	 * resource.
	 * 
	 * @see gov.g5.support.initialization.Startable#shutDown()
	 */
	public boolean shutDown() {
		jndiSessionObject = null;
		return true;
	}

	/**
	 * Takes the opportunity to perform the JNDI lookup of the email session
	 * resource while the thread is guaranteed to be associated with the web
	 * container. Waiting to do this say, from a scheduled process whose thread
	 * is not a Web container thread will not be sucessful.
	 * 
	 * @return True if the JNDI Javamail resource could be found, false
	 *         otherwise.
	 */
	public boolean startUp() {
		try {
			log.info("Initializing EmailSender now.");
//			Context context = new InitialContext();
//			jndiSessionObject = context.lookup("java:comp/env/MailSession");
//			log.info("Found JNDI session object in context java:comp/env/MailSession: " + jndiSessionObject);
		} catch (Exception e) {
			log.warn("Unable to obtain JavaMail session object from application server resource. " + e);
			return false;
		}
		return true;
	}

	/**
	 * Adds an Email message to the deferred message queue for subsequent
	 * transmission at commit time. The sender must be open for this operation.
	 * 
	 * @see com.cisco.cstg.autotools.integration.email.EmailWorkUnit#submitEmailMessage(com.cisco.cstg.autotools.integration.email.EmailMessage)
	 */
	public void submitEmailMessage(EmailMessage email) {
		this.open();
		Assertion.isValidReference(this.session, "The sender must be open to perform this operation.");
		this.messages.add(email);
		this.commit();
		this.close();
	}

	/**
	 * Cleans up the sender, discarding the JavaMail session.
	 * 
	 * @see gov.g5.support.Transaction.WorkUnit#close()
	 */
	public void close() {
		Assertion.isValidReference(this.session, "The sender must be open to perform this operation.");
		this.session = null;
	}

	/**
	 * Transmits the set of Email messages that were deferred within the sender.
	 * 
	 * @see gov.g5.support.Transaction.WorkUnit#commit()
	 */
	public void commit() {
		Assertion.isValidReference(this.session, "The sender must be open to perform this operation.");
		Iterator i = this.messages.iterator();
		while (i.hasNext()) {
			sendMail((EmailMessage) i.next());
		}
		this.messages.clear();
	}

	/**
	 * Prepares the sender for sending emails by opening a JavaMail session.
	 * This method depends on the application server's mail provider being
	 * configured to identify the SMTP server and the information needed to open
	 * a mail session.
	 * 
	 * @see gov.g5.support.Transaction.WorkUnit#open()
	 */
	public void open() {
		 Properties props = new Properties();
		 props.setProperty("mail.transport.protocol", "smtp");
		 props.setProperty("mail.host", config.getStringValue(MAIL_SERVER));
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.port", config.getStringValue(MAIL_PORT));
		// props.put("mail.smtp.socketFactory.port",
		// config.getStringValue(MAIL_PORT));
		// props.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.socketFactory.fallback", "false");
		// props.setProperty("mail.smtp.quitwait", "false");
		//
		 this.session = Session.getDefaultInstance(props, null);
		// javax.mail.Authenticator() {
		// protected PasswordAuthentication getPasswordAuthentication() {
		// return new
		// PasswordAuthentication(config.getStringValue(MAIL_ACCOUNT), config
		// .getStringValue(MAIL_PASSWORD));
		// }
		// });
		// this.session.setDebug(config.getBooleanValue(DEBUG));

//		log.debug("Mail Session: " + jndiSessionObject);
//		if ( jndiSessionObject != null)
//			log.debug("Session class: " + jndiSessionObject.getClass());
//		this.session = (Session) jndiSessionObject;
		log.info("Opening email sender now.  Setting session value in instance : " + this.session);
		this.session.setDebug(config.getBooleanValue(DEBUG));
	}

	/**
	 * Discards any deferred Email messages, thus rolling back the Email unit of
	 * work.
	 * 
	 * @see gov.g5.support.Transaction.WorkUnit#rollback()
	 */
	public void rollback() {
		Assertion.isValidReference(this.session, "The sender must be open to perform this operation.");
		messages.clear();
	}

	/**
	 * Transmits an Email message through the configured SMTP server. If the
	 * SMTP server can not be reached or does not respond or any other failure
	 * prevents the message from being sent, a warning message containing the
	 * entire email and the reason for failure written to the system log.
	 * 
	 * @param email
	 *            The Email message to send.
	 */
	private void sendMail(EmailMessage email) {
		MimeMessage message = new MimeMessage(this.session);
		try {
			message.setSubject(email.getSubject());
			message.setRecipients(Message.RecipientType.TO, email.getToAddresses());
			message.setRecipients(Message.RecipientType.CC, email.getCopyAddresses());
			if (email.getFromAddress() != null) {
				message.setFrom(email.getFromAddress());
			}
			if (!email.isMultipart()) {
				message.setContent(email.getBody(), email.getContentType());
			} else {
				// sending a multipart message
				Multipart multiPartContent = new MimeMultipart();
				// set the email message 
				MimeBodyPart body = new MimeBodyPart();
				body.setText(email.getBody(), UTF_8);
				multiPartContent.addBodyPart(body);
				
				EmailAttachment[] attachments = email.getAttachments();
				// traverse the attachments and add them
				for (int i = 0; attachments != null && i < attachments.length; i++) {
					FileDataSource source = attachments[i].getDatasource();
					MimeBodyPart attachment = new MimeBodyPart();
					// set the data onto the object
					attachment.setDataHandler(new DataHandler(source));
					attachment.setFileName(attachments[i].getShortFilename());
					multiPartContent.addBodyPart(attachment);
				}
				// set the attachments
				message.setContent(multiPartContent);
			}
			Transport.send(message);
		} catch (Exception exception) {
			log.warn("Failed to send Email message. Reason: " + exception + "\n" + email);
		}
	}

}
