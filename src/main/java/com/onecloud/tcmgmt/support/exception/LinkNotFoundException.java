package com.onecloud.tcmgmt.support.exception;

public class LinkNotFoundException extends Contingency {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5392819945563616752L;
	
	public LinkNotFoundException () {
        super();
    }
	public LinkNotFoundException (String message) {
		super(message);
	}

}
