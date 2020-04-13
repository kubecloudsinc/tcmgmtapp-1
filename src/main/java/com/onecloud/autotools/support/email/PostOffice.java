package com.onecloud.autotools.support.email;

import java.util.Map;

import com.onecloud.autotools.semantic.diagnostics.ValidationException;
import com.onecloud.autotools.semantic.diagnostics.ValidationException;

/**
 * An object that is capable of sending Email messages, integrating with the
 * work context for the current process. Email messages sent through a
 * PostOffice will not be transmitted until the work context reaches a commit
 * point.
 * 
 * @author sichituk
 * 
 */
public interface PostOffice {

	/**
	 * Sends a deferred Email message which will be held internally until the
	 * next commit point.
	 * 
	 * @param workContext
	 *            The work context under which the message will be deferred and
	 *            sent upon commit.
	 * @param email
	 *            The Email message object, containing subject, body, and
	 *            addressing information.
	 */
	public void sendEmail(EmailMessage email);

	/**
	 * Sends a deferred Email message which will be held internally until the
	 * next commit point.
	 * 
	 * @param workContext
	 *            The work context under which the message will be deferred and
	 *            sent upon commit.
	 * @param nameOfEmailTemplate
	 *            The name of the email template located in the emailTemplates
	 *            configuration folder.
	 * @param insertions
	 *            The mappings of insertions the replace text in the email
	 *            template.
	 * @param toAddresses
	 *            The list of email addresses to which the email should be sent.
	 * @param ccAddresses
	 *            The list of cc email addresses to which the email should be
	 *            sent.
	 * @throws ValidationException
	 *             Thrown if an email cannot be sent. Currently this will only
	 *             happen if an email is not valid.
	 * @see com.cisco.nettools.support.email.EmailContent EmailContent for an explantion of mapped insertions
	 */
	public void sendEmail(String nameOfEmailTemplate,
			Map insertions, String[] toAddresses, String[] ccAddresses)
			throws ValidationException;
	
	/**
	 * Sends a deferred Email message which will be held internally until the
	 * next commit point.
	 * 
	 * @param workContext
	 *            The work context under which the message will be deferred and
	 *            sent upon commit.
	 * @param nameOfEmailTemplate
	 *            The name of the email template located in the emailTemplates
	 *            configuration folder.
	 * @param insertions
	 *            The mappings of insertions the replace text in the email
	 *            template.
	 * @param toAddresses
	 *            The list of email addresses to which the email should be sent.
	 * @param ccAddresses
	 *            The list of cc email addresses to which the email should be
	 *            sent.
	 * @param attachments
	 *            The list of attachments that need to be sent along with the mail
	 * @throws ValidationException
	 *             Thrown if an email cannot be sent. Currently this will only
	 *             happen if an email is not valid.
	 * @see com.cisco.nettools.support.email.EmailContent EmailContent for an explantion of mapped insertions
	 */
	public void sendEmail(String nameOfEmailTemplate, Map insertions, String[] toAddresses, 
	String[] ccAddresses, EmailAttachment[] attachments) throws ValidationException;
}
