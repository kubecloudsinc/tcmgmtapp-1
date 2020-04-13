package com.onecloud.autotools.support.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.onecloud.autotools.semantic.diagnostics.Diagnostic;
import com.onecloud.autotools.semantic.diagnostics.ValidationException;

/**
 * Accepts Email messages for subsequent transmission at commit time. The main
 * job of this class is to manage the relationship between the work context for
 * the process and the work unit object needed to handle email transmissions in
 * a transaction-like way.
 * 
 * 
 * @author sichituk
 * 
 */
public class SystemPostOffice implements PostOffice {
	/**
	 * Accepts an EmailMessage for eventual transmission at commit time. This
	 * method attaches an Email Work Unit to the supplied Work Context and then
	 * submits the message through the new or discovered work unit.
	 * 
	 * @see com.cisco.cstg.autotools.integration.email.PostOffice#sendEmail(gov.g5.support.Transaction.WorkContext,
	 *      com.cisco.cstg.autotools.integration.email.EmailMessage)
	 */
	public void sendEmail(EmailMessage email) {
		EmailSender sender = new EmailSender();
		sender.submitEmailMessage(email);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.g5.integration.email.PostOffice#sendEmail(gov.g5.support.Transaction.WorkContext,
	 *      java.lang.String, java.util.Map, java.lang.String[],
	 *      java.lang.String[])
	 */
	public void sendEmail(String nameOfEmailTemplate,
			Map insertions, String[] toAddresses, String[] ccAddresses)
			throws ValidationException {
		
		EmailCatalog catalog = new EmailLocator();
		EmailContent content = catalog.lookUp(nameOfEmailTemplate);
		String subject = content.getEditedSubject(insertions);
		String body = content.getEditedBody(insertions);

		EmailDestination[] toDestinations = new EmailDestination[toAddresses.length];
		
		EmailDestination[] ccDestinations = null;
		if(ccAddresses!=null && ccAddresses.length > 0) {
			ccDestinations = new EmailDestination[ccAddresses.length];
		} else {
			ccDestinations = new EmailDestination[0];
		}

		List diagnosticList = new ArrayList();

		EmailAddress address = null;
		for (int i = 0; i < toAddresses.length; i++) {
			try {
				address = new EmailAddress(toAddresses[i]);
				toDestinations[i] = address.getDestination();
			} catch (ValidationException e) {
				diagnosticList.add(e.getDiagnostics()[0]);
			}
		}

		for (int i = 0; i < ccDestinations.length; i++) {
			try {
				address = new EmailAddress(ccAddresses[i]);
				ccDestinations[i] = address.getDestination();
			} catch (ValidationException e) {
				diagnosticList.add(e.getDiagnostics()[0]);
			}
		}

		if (diagnosticList.size() > 0)
			throw new ValidationException((Diagnostic[]) diagnosticList
					.toArray(new Diagnostic[diagnosticList.size()]));

		EmailMessage message = null;
		
		message = new EmailHtmlMessage(subject, body, toDestinations,
				ccDestinations);
		
		sendEmail(message);
	}
	
	/* (non-Javadoc)
	 * @see gov.g5.integration.email.PostOffice#sendEmail(gov.g5.support.Transaction.WorkContext, java.lang.String, java.util.Map, java.lang.String[], java.lang.String[], gov.g5.integration.email.EmailAttachment[])
	 */
	public void sendEmail(String nameOfEmailTemplate,
			Map insertions, String[] toAddresses, String[] ccAddresses, EmailAttachment[] attachments)
			throws ValidationException {
		
		EmailCatalog catalog = new EmailLocator();
		EmailContent content = catalog.lookUp(nameOfEmailTemplate);
		String subject = content.getEditedSubject(insertions);
		String body = content.getEditedBody(insertions);

		EmailDestination[] toDestinations = new EmailDestination[toAddresses.length];
		EmailDestination[] ccDestinations = new EmailDestination[ccAddresses.length];

		List diagnosticList = new ArrayList();

		EmailAddress address = null;
		for (int i = 0; i < toAddresses.length; i++) {
			try {
				address = new EmailAddress(toAddresses[i]);
				toDestinations[i] = address.getDestination();
			} catch (ValidationException e) {
				diagnosticList.add(e.getDiagnostics()[0]);
			}
		}

		for (int i = 0; i < ccAddresses.length; i++) {
			try {
				address = new EmailAddress(ccAddresses[i]);
				ccDestinations[i] = address.getDestination();
			} catch (ValidationException e) {
				diagnosticList.add(e.getDiagnostics()[0]);
			}
		}

		if (diagnosticList.size() > 0)
			throw new ValidationException((Diagnostic[]) diagnosticList
					.toArray(new Diagnostic[diagnosticList.size()]));

		EmailMessage message = new EmailMessage(subject, body, toDestinations,
				ccDestinations);
		
		if (attachments != null &&  attachments.length > 0) {
			message.setAttachments(attachments);
			message.setMultipart(true);
		}
		
		sendEmail(message);
	}

}