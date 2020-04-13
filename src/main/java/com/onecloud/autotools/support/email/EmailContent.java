package com.onecloud.autotools.support.email;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsulates the content portion of an Email message, specifically its
 * subject line and body, allowing insertions to be, well, inserted.
 * 
 * @author sichituk
 * 
 */
public class EmailContent {
	private String subject = null;

	private String body = null;

	/**
	 * Constructs an instance of the class with a subject and a body. Both the
	 * subject and the body may contain insertion markers delimited by { and }
	 * characters. These markers mark the places in the content that the class
	 * can insert information to customize the content.
	 * <p>
	 * Insertions take the form of {characters}, where characters are
	 * alphanumeric, non-space, non-special characters. Otherwise, { and }
	 * characters are just part of the text. There is no provision to escape {
	 * or } sentinels.
	 * 
	 * @param subject
	 *            A string containing the template of the subject line for the
	 *            email. The template can contain {inserts}.
	 * @param body
	 *            A string containing the template of the body of the email. The
	 *            template can contain {inserts}, and formatting characters such
	 *            as new lines, tabs, etc.
	 */
	public EmailContent(String subject, String body) {
		super();
		this.subject = subject;
		this.body = body;
	}

	/**
	 * Edits the subject line of the content, inserting information carried in
	 * the insertions map into the output.
	 * 
	 * @param insertions
	 *            A map whose keys correspond to insertion markers in the
	 *            subject line that look like {insertionkey} and whose values
	 *            correspond to the information to be inserted in place of the
	 *            markers in the subject line. Any object can be used as a value -
	 *            its toString() value will appear in the edited text.
	 * @return The edited subject line of the content.
	 */
	public String getEditedSubject(Map insertions) {
		return edit(this.subject, insertions);
	}

	/**
	 * Edits the body of the content, inserting information carried in the
	 * insertions map into the output.
	 * 
	 * @param insertions
	 *            A String-valued map whose keys correspond to insertion markers
	 *            in the text that look like {insertionkey} and whose values
	 *            correspond to the information to be inserted in place of the
	 *            markers in the text. Any object can be used as a value - its
	 *            toString() value will appear in the edited text.
	 * @return The edited body of the content.
	 */
	public String getEditedBody(Map insertions) {
		return edit(this.body, insertions);
	}

	/**
	 * Edits a template text, inserting the values from a Map in place of the
	 * marked insertions.
	 * 
	 * @param text
	 *            The template text sample to edit, containing insertion
	 *            markers.
	 * @param insertions
	 *            A String-keyed map whose keys correspond to insertion markers
	 *            in the text (without the brackets) and whose values correspond
	 *            to the information to be inserted in place of the markers in
	 *            the text. Any object can be used as a value - its toString()
	 *            value will appear in the edited text.
	 * @return The edited text - if the insertions map does not contain a
	 *         mapping for an insertion marker, that marker will be retained in
	 *         the text. Otherwise, the markers will be replaced by their
	 *         replacement values in the map.
	 */
	private String edit(String text, Map insertions) {
		String insertionExpression = "\\{\\w+?\\}";
		Pattern pattern = Pattern.compile(insertionExpression);
		Matcher matcher = pattern.matcher(text);
		Set markers = new HashSet();
		while (matcher.find()) {
			String marker = matcher.group();
			markers.add(marker.substring(1, marker.length() - 1));
		}
		String editedText = text;
		Iterator i = markers.iterator();
		while (i.hasNext()) {
			String markerValue = (String) i.next();
			Object replacement = insertions.get(markerValue);
			if (replacement != null) {
				String markerExpression = "\\{" + markerValue + "\\}";
				pattern = Pattern.compile(markerExpression);
				matcher = pattern.matcher(editedText);
				editedText = matcher.replaceAll(replacement.toString());
			}
		}
		return editedText;
	}
}
