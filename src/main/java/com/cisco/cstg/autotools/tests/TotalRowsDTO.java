package com.cisco.cstg.autotools.tests;

/**
 * Data transfer object which carries the following info:
 * 	<ul>
 * 		<li>The total number of rows displayed on page</li>
 * 		<li>if any staleElement excetion happened during finding the total rows</li>
 * 	</ul>
 * 
 * the need for this is for accuarate time taken for the page to load. 
 *   For any exception it is taking the implict time so that much time is wasted.
 *   
 * @author sichituk
 *
 */
public class TotalRowsDTO {
	
	/**
	 * The total number of rows sting shown on the page in the format "<<total>> rows".
	 */
	private String totalRows;
	
	private boolean staleElementExceptionOccured;

	/**
	 * Default constructor forces to initialize the values.
	 * 
	 * @param totalRows
	 * @param hasStaleElementExceptionOccured
	 */
	public TotalRowsDTO(String totalRows,
			boolean hasStaleElementExceptionOccured) {
		super();
		this.totalRows = totalRows;
		this.setStaleElementExceptionOccured(hasStaleElementExceptionOccured);
	}

	public String getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}

	public boolean isStaleElementExceptionOccured() {
		return staleElementExceptionOccured;
	}

	public void setStaleElementExceptionOccured(boolean staleElementExceptionOccured) {
		this.staleElementExceptionOccured = staleElementExceptionOccured;
	}


}