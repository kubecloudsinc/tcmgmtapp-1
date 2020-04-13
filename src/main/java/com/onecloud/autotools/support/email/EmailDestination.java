package com.onecloud.autotools.support.email;

import javax.mail.internet.InternetAddress;

/**
 * Encapsulates an Email Internet Address, allowing unit tests to function
 * without including the JavaMail library.
 * 
 * @author sichituk
 * 
 */
public class EmailDestination {
	InternetAddress address = null;

	/**
	 * Creates an instance of the class.
	 * 
	 * @param address
	 *            An JavaMail Internet Address.
	 */
	public EmailDestination(InternetAddress address) {
		super();
		this.address = address;
	}

	/**
	 * Obtains the underlying Email internet address.
	 * 
	 * @return The address.
	 */
	public InternetAddress getAddress() {
		return address;
	}

}
