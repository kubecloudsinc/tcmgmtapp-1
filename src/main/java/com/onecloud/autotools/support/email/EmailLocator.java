package com.onecloud.autotools.support.email;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import com.onecloud.autotools.support.configuration.ConfigurationFile;
import com.onecloud.autotools.support.exception.Assertion;
import com.onecloud.autotools.support.exception.Fault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Locates configured Email samples, obtaining the version of a sample whose
 * version matches the System execution environment. This is an implementation
 * of the EmailCatalog interface.
 * 
 * @author sichituk
 * 
 */
public class EmailLocator implements EmailCatalog {
	private static final Logger log = LoggerFactory.getLogger(EmailLocator.class);

	/**
	 * Obtains the content of an Email message from system configuration
	 * information based on its itemName.
	 * 
	 * @see EmailCatalog#lookUp(java.lang.String)
	 */
	public EmailContent lookUp(String itemName) {
		ConfigurationFile emailTemplate = new ConfigurationFile(itemName, "smtp");
		InputStream stream = emailTemplate.open();
		Assertion.isValidReference(stream, "Unable to locate Email template file for " + itemName);
		LineNumberReader reader = new LineNumberReader(new InputStreamReader(stream));
		StringBuffer body = new StringBuffer();
		String subject = null;
		String nextLine = null;
		boolean foundSubject = false;
		boolean foundBody = false;
		try {
			while ((nextLine = reader.readLine()) != null) {
				switch (reader.getLineNumber()) {
				case 1:
					Assertion.isTrue(nextLine.trim().equalsIgnoreCase("[subject]"),
							"Invalid [subject] sentinel in Email template for " + itemName);
					break;
				case 2:
					subject = nextLine;
					foundSubject = true;
					break;
				case 3:
					Assertion.isTrue(nextLine.trim().equalsIgnoreCase("[body]"),
							"Invalid [body] sentinel in Email template for " + itemName);
					break;
				default:
					body.append(nextLine + "\n");
					foundBody = true;
					break;
				}
			}
		} catch (IOException e) {
			throw new Fault("Unexpected I/O exception reading Email Template file.", e);
		}
		Assertion.isTrue(foundSubject, "Unable to locate subject line in Email template for " + itemName);
		Assertion.isTrue(foundBody, "Unable to locate body in Email template for " + itemName);
//		log.debug("Subject:\n" + subject.toString());
//		log.debug("Body:\n" + body.toString());
		return new EmailContent(subject, body.toString());
	}
}
