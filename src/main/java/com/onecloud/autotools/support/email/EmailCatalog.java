package com.onecloud.autotools.support.email;

/**
 * A Catalog of the configured Email text samples contained in the system. Email
 * samples take the form of text files whose format given below. 
 * <pre> 
 * 1 [subject]
 * 2 Subject line with {insertion} appears on this line.
 * 3 [body]
 * 4 All remaining text is body text starting at this line.  It may have insertion
 * 5 {markers} embedded within it.  Text is rendered in messages
 * 6 exactly how it is formatted in the text file except that
 * 7 {insertions} are replaced with actual information.
 * </pre>
 * 
 * <p>
 * All Email templates are maintained in the /emailTemplates directory and their names allow
 * versioning to be performed.  The format of the names is:
 * <p>
 * ItemName[-version].smtp
 * <p>
 * Where ItemName base name of the Email text sample file, version is an optional version
 * identifier, and smtp is the file extension.  The implementation will first look for a version
 * of the file that matches the configured system execution environment.  If it finds one,
 * it returns it.  If not, it will look for a base version (one that has no version name).
 * If it finds one, it returns it.  Otherwise, it will return a null reference.
 * 
 * @author sichituk
 *
 */
public interface EmailCatalog {

	/**
	 * Obtains the content of an Email text contained in the catalog.
	 * 
	 * @param itemName
	 *            The name of the item in the catalog to find.
	 * @return An EmailContent object that contains the desired content, or null
	 *         if the itemName did not locate any item in the catalog.
	 */
	public EmailContent lookUp(String itemName);

}
