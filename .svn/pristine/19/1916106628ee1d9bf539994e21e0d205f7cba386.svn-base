package com.cisco.cstg.autotools.support.enumeration;


public class LNPLinkType implements NamespaceElement {
	/**
	 * The namespace for the enumeration.
	 */
	private static Namespace namespace = new Namespace();

	/**
	 * The unique string value corresponding to each constant member of the
	 * enumeration.
	 */
	private String key = null;

	/**
	 * Obtains a copy of the namespace object for the enumeration. This allows
	 * access to the complete set of constants defined in the enumeration.
	 *
	 * @return A copy of the enumeration's namespace.
	 */
	public static Namespace getNamespace() {
		return namespace.copy();
	}

	/**
	 * Private constructor for constant members of the enumeration.
	 *
	 * @param key
	 *            The unique key value to associate with the constant member.
	 */
	protected LNPLinkType(String key) {
		this.key = key;
		namespace.addElement(this);
	}

	/*
	 * @see gov.g5.support.enumeration.NamespaceElement#getKey()
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Returns the key value of the constant.
	 *
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getKey();
	}

	// /////////////////////////////////
	// Start of LNP Definitions //
	// /////////////////////////////////

	
	public static final LNPLinkType GENERAL_LINK = new LNPLinkType("LINK");
	public static final LNPLinkType DASHBOARD_LINK = new LNPLinkType("DASHBOARD");
	public static final LNPLinkType LIBRARY_LINK = new LNPLinkType("LIBRARY");
}