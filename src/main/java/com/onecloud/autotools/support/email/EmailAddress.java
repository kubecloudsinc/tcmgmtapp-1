package com.onecloud.autotools.support.email;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.onecloud.autotools.support.email.EmailDestination;
import com.onecloud.autotools.semantic.diagnostics.ConditionCode;
import com.onecloud.autotools.semantic.diagnostics.Diagnostic;
import com.onecloud.autotools.semantic.diagnostics.ValidationException;
import com.onecloud.autotools.semantic.diagnostics.ConditionCode;
import com.onecloud.autotools.semantic.diagnostics.Diagnostic;
import com.onecloud.autotools.semantic.diagnostics.ValidationException;

/**
 * An Email address that is suitable for use in the Email complex, having been
 * validated for suitable syntax. It may be used to obtain a syntatically valid
 * InternetAddress object.  You can use this domain object to validate Email
 * addresses that are submitted from the Access Tier.
 * 
 * @author sichituk
 * 
 */
public class EmailAddress {
	private EmailDestination destination = null;

	/**
	 * Constructs an Email address from a conforming string.
	 * 
	 * @param address
	 *            The Email address to validate and encapsulate in the
	 *            constructed instance.
	 * 
	 * @throws ValidationException
	 *             If the address string fails the syntax check for a valid
	 *             InternetAddress. This exception will contain a single
	 *             diagnostic indicating an invalid email address error
	 *             containing the offending address string value.
	 */
	public EmailAddress(String address) throws ValidationException {
		InternetAddress internetAddress = null;
		try {
			internetAddress = new InternetAddress(address,true);
		} catch (AddressException e) {
			Diagnostic diagnostic = new Diagnostic(ConditionCode.ERROR_INVALID_EMAIL_ADDRESS, address);
			throw new ValidationException(new Diagnostic[] { diagnostic });
		}
		this.destination = new EmailDestination(internetAddress);
	}

	/**
	 * Obtains the destination address encapsulated within the instance.
	 * 
	 * @return A valid Email destination object reflecting the Email address.
	 */
	public EmailDestination getDestination() {
		return destination;
	}

}
