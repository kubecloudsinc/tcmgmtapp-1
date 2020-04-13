package com.onecloud.autotools.semantic.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.onecloud.autotools.domain.appdb.TempUser;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onecloud.autotools.support.configuration.ComponentConfiguration;
import com.onecloud.autotools.support.configuration.Configuration;

public class ApplicationConstants {
	
	public static final Logger logger = LoggerFactory.getLogger(ApplicationConstants.class);
	
	public static final String APPLICATION_DEFAULT_ENCODING_KEY = "applicationEncoding";
	public static final String REPORTS_FOLDER_KEY = "reportsFolder";
	public static final String REPORT_FILE_EXTENSION_KEY = "reportFileExtension";
	public static final String REPORT_FILENAME_STARTING_STRING_KEY = "reportFileStartingString";
	public static final String DATE_FORMAT_IN_REPORT_FILENAME_KEY ="dateFormatInReportFile";
	public static final String LEVEL_DIFF_BETWEEN_REPORTS_FOLDER_AND_CLASSPATH_KEY ="diffInLevels";
	public static final String LEVEL_ROOT_INDEX_KEY ="rootIndex";
	/**
	 * Application related configuration.
	 */
	public static Configuration APPLICATION_CONFIG = new ComponentConfiguration(ApplicationConstants.class);
	
	
	public static final String APPLICATION_DEFAULT_ENCODING = APPLICATION_CONFIG.getStringValue(APPLICATION_DEFAULT_ENCODING_KEY);
	
	public static final String REPORTS_FOLDER = APPLICATION_CONFIG.getStringValue(REPORTS_FOLDER_KEY);
	public static final String REPORT_FILE_EXTENSION = APPLICATION_CONFIG.getStringValue(REPORT_FILE_EXTENSION_KEY);
	public static final String REPORT_FILENAME_STARTING_STRING = APPLICATION_CONFIG.getStringValue(REPORT_FILENAME_STARTING_STRING_KEY);
	public static final String DATE_FORMAT_IN_REPORT_FILENAME = APPLICATION_CONFIG.getStringValue(DATE_FORMAT_IN_REPORT_FILENAME_KEY);
	public static final int LEVEL_DIFF_BETWEEN_REPORTS_FOLDER_AND_CLASSPATH = 
			APPLICATION_CONFIG.getIntegerValue(LEVEL_DIFF_BETWEEN_REPORTS_FOLDER_AND_CLASSPATH_KEY);
	public static final int LEVEL_ROOT_INDEX = APPLICATION_CONFIG.getIntegerValue(LEVEL_ROOT_INDEX_KEY);
	
	
	public static final String REGEX_TO_GET_DIRECTORY_NAME_FROM_FILEPATH = "^(.*)/";
	
	public static final String convertToJavaFileName(String fileNameToBeConverted){
		return fileNameToBeConverted.replace("\\", "/");
	}
	
	public static final String getParentDirectoryFromRegex(String fileName){
		Matcher matcher = Pattern.compile(ApplicationConstants.REGEX_TO_GET_DIRECTORY_NAME_FROM_FILEPATH).matcher(fileName);
		matcher.find();
		return matcher.group();
	}
	
	
	public static final String getUniqueReportName(){
		
		String reportFileName = ApplicationConstants.REPORT_FILENAME_STARTING_STRING	+
								(new SimpleDateFormat(ApplicationConstants.DATE_FORMAT_IN_REPORT_FILENAME).format(new Date()))
									.replaceAll("\\s", "-")
									.replaceAll(":", "")
								+ApplicationConstants.REPORT_FILE_EXTENSION;
//		logger.debug("The Report File Name is: {}", reportFileName);
		return reportFileName;
	}
	
	public static final Path getClasspathDirectory(){
		File someFileOnClassPath = 
				new File(Thread.currentThread().getContextClassLoader().getResource(TempUser.class.getName()+".cfg").getFile());

		// get the Directory name of the file.
		String directoryNameOfsomeFileOnClassPath = ApplicationConstants.convertToJavaFileName(someFileOnClassPath.getParent());
		Path pathOfClasspathDirectory = Paths.get(directoryNameOfsomeFileOnClassPath);
		
		return pathOfClasspathDirectory;
	}
	
	public static final Path getReportsDirectory(){
		File someFileOnClassPath = 
				new File(Thread.currentThread().getContextClassLoader().getResource(TempUser.class.getName()+".cfg").getFile());
//		String filePathOfsomeFileOnClassPath = someFileOnClassPath.getAbsolutePath();
//		logger.debug("File path AFTER is: {}",filePathOfsomeFileOnClassPath);

		// get the Directory name of the file.
		String directoryNameOfsomeFileOnClassPath = ApplicationConstants.convertToJavaFileName(someFileOnClassPath.getParent());
		Path pathOfSomeFileDirectory = Paths.get(directoryNameOfsomeFileOnClassPath);
		
		// get reports directory path.
		Path reportsDirectoryPath = 
				Paths.get(pathOfSomeFileDirectory.getRoot().toString(),
				pathOfSomeFileDirectory.subpath
					(ApplicationConstants.LEVEL_ROOT_INDEX, 
							(pathOfSomeFileDirectory.getNameCount()-ApplicationConstants.LEVEL_DIFF_BETWEEN_REPORTS_FOLDER_AND_CLASSPATH)).toString(),
				File.separator,ApplicationConstants.REPORTS_FOLDER,File.separator);
		
//		logger.debug("Directory path: {}, subpath to reports directory: {}, newPath for reports: {}"
//				,pathOfSomeFileDirectory
//				,pathOfSomeFileDirectory.subpath(0, (pathOfSomeFileDirectory.getNameCount()-2))
//				,reportsDirectoryPath);
		return reportsDirectoryPath;
	}
	
	public static final String getUniqueReportURL(){
//		logger.debug("The URL is: {}",testReportURL);
		return ApplicationConstants.convertToJavaFileName(
				File.separator+ApplicationConstants.REPORTS_FOLDER+File.separator +getUniqueReportName());
	}
	
	public static final String getReportNameFromURL(String reportURL){
		return Paths.get(reportURL).getFileName().toString();
	}
	
	
	/**
	 * compare the customer names ignoring the spaces
	 */
	public static final boolean compareCustomerNames(String readCustomerName, String givenCustomerName){
		boolean isValueSame = true;
		String [] readSplits = readCustomerName.trim().split("\\s+");
//		for (int i = 0; i < readSplits.length; i++) {
//			logger.debug("The split value:{}",readSplits[i]);
//		}
		String [] givenSplits = givenCustomerName.trim().split("\\s+");
//		for (int i = 0; i < givenSplits.length; i++) {
//			logger.debug("the choice split:{}",givenSplits[i]);
//		}
		
		// compare the lengths first
		if(readSplits.length==givenSplits.length){
			for (int i = 0; i < givenSplits.length; i++) {
				if(readSplits[i].compareToIgnoreCase(givenSplits[i])!=0){
					isValueSame=false;
				}
			}
		}else{
			isValueSame=false;
		}
		return isValueSame;
	}
	
	public void parseJSON(){
		
		Logger logger = ApplicationConstants.logger;
		File csvFile = new File(ApplicationConstants.getClasspathDirectory().toString(),"DeviceList_728.csv");
		try {
			List<String> lines = FileUtils.readLines(csvFile);
			String [] headerStrings = lines.get(0).split(",",-1);
			String [] valueStrings = lines.get(1).split(",",-1);
			
			List<Integer> deviceIdMarkers = new ArrayList<Integer>();
			Integer aInteger;
			for (int i=0;i<headerStrings.length;i++){
				if(headerStrings[i].contains(".DEVICE_ID")){
					aInteger = new Integer(i);
					deviceIdMarkers.add(aInteger);
					logger.debug("Found device id at position: {}",i);
				}
			}
			// find which one is bigger the header string counts or 
			int smallerCount;
			if(headerStrings.length>=valueStrings.length){
				smallerCount = valueStrings.length;
			}else{
				smallerCount = headerStrings.length;
			}
			logger.debug("The headerString size is: {}",headerStrings.length);
			logger.debug("The ValuesString size is: {}",valueStrings.length);
			logger.debug("The smaller size of both: {}",smallerCount);
			
			List<String> deviceId = new ArrayList<String>();
			int j=0;
			for (int i = 0; i < smallerCount; i++) {
				if(j<deviceIdMarkers.size()){
					int comparingIntValue = ((Integer)deviceIdMarkers.get(j)).intValue();
					if(i==comparingIntValue){
						// get the data
						deviceId.add(valueStrings[i]);
						
						logger.debug("Found the device id at position i:{} and the compared values is j:{}",i,j);
						logger.debug("the device id is:{}",valueStrings[i]);
						j++;
					}
				}else{
					break;
				}
			}
			FileUtils.writeLines(csvFile, deviceId, true);
//			(new File(ApplicationConstants.getClasspathDirectory().toString(),"deviceIds.txt"), deviceId, " ", true);
			
		} catch (IOException e) {
			final Writer result = new StringWriter();
		    final PrintWriter printWriter = new PrintWriter(result);
		    e.printStackTrace(printWriter);
		    logger.info(result.toString());
		}
	}
}