package com.cisco.cstg.autotools.semantic.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.cstg.autotools.dao.ScheduleDao;
import com.cisco.cstg.autotools.domain.appdb.Schedule;
import com.cisco.cstg.autotools.domain.appdb.TempUser;
import com.cisco.cstg.autotools.tests.TestConstants;
import com.cisco.cstg.autotools.teststhreads.PSSPageLoadTimeTestThread;
import com.cisco.cstg.autotools.teststhreads.SNTCPageLoadTimeTestThread;
import com.cisco.cstg.autotools.web.viewbean.ScheduleViewBean;

@Service
public class ScheduleManager extends BaseManager 
                             implements ScheduleMonitor{

	private static final Logger logger = LoggerFactory.getLogger(ScheduleManager.class);
	protected static final int SNTC_RUN_FREQUENCY = testConfig.getIntegerValue(TestConstants.PAGE_LOAD_MONITOR_RUN_FREQUENCY);
	protected static final int SNTC_INITIAL_DELAY = testConfig.getIntegerValue(TestConstants.PAGE_LOAD_MONITOR_INITIAL_DELAY);
	protected static final int PSS_RUN_FREQUENCY = testConfig.getIntegerValue(TestConstants.PSS_PAGE_LOAD_MONITOR_RUN_FREQUENCY);
	protected static final int PSS_INITIAL_DELAY = testConfig.getIntegerValue(TestConstants.PSS_PAGE_LOAD_MONITOR_INITIAL_DELAY);	
	protected static final String MONITOR_FREQUENCY_UNIT = testConfig.getStringValue(TestConstants.MONITOR_FREQUENCY_UNIT);	
	private static ScheduledExecutorService sntcExecutor;
	private static ScheduledExecutorService pssExecutor;
	
	@Autowired
    private ScheduleDao scheduleDao;
	
    /**
     * Method to toggle scheduler status; will change to stop if scheduled and vice versa.
     */
	@Override
	public void toggle(Long scheduleId, String userName, String password) {
		
		logger.debug("INSIDE THE toggle method");

		// get the current status of the schedule. 
		Schedule schedule = scheduleDao.getById(scheduleId);
		
		logger.debug("FETCHED THE Schedule object: "+schedule.getScheduleName());
		
		if(schedule.getProgramName().equals(Schedule.SNTC_PROGRAM)){
			scheduleAThread(schedule, sntcExecutor, new SNTCPageLoadTimeTestThread(),SNTC_INITIAL_DELAY, SNTC_RUN_FREQUENCY, userName, password );
		}else if(schedule.getProgramName().equals(Schedule.PSS_PROGRAM)){
			scheduleAThread(schedule, pssExecutor, new PSSPageLoadTimeTestThread(), PSS_INITIAL_DELAY, PSS_RUN_FREQUENCY, userName, password);
		}else{
			logger.info("NO SCHEDULER FOUND FOR THE GIVEN SELECTION");
		}
		
		scheduleDao.save(schedule);

		logger.debug("END the toggle");
//		scheduleDao.clearSessions();
	}

	@Override
	public List<ScheduleViewBean> getAllSchedules() {
        
		List<ScheduleViewBean> returnList = new ArrayList<ScheduleViewBean>();
		logger.debug("INSIDE SET UP FORM");
        List<Schedule> result = scheduleDao.getAll();
        logger.debug("Got {} entities", result.size());
        for (Schedule schedule : result) {
			returnList.add(new ScheduleViewBean().scheduleToScheduleViewBean(schedule));
		}
        return returnList;
	}
	
	private void scheduleAThread(Schedule schedule, ScheduledExecutorService executor, 
			Runnable runnableImplementationClass, int initialDelay, int runFrequency,
					String userName, String password){
		
		if(schedule.getStatus().equalsIgnoreCase(Schedule.SCHEDULED)){
			logger.debug("THE STATUS IS SCHEDULED");
			// db says it scheduled; checking for the sntcExecutor
			if(executor!=null && !(executor.isShutdown())){
				executor.shutdown();
				logger.debug("SCHEDULER IS SHUTDOWN");
			}
			// else anyways we want to shut down so don't have to do anything
			//update the DB status that the scheduler is shutdown.
			schedule.setStatus(Schedule.STOPPED);
			logger.debug("SCHEDULER IS UPDATED TO STOPPED");
		}else{
			logger.debug("SCHEDULER IS STOPPED");
			//update the TempUser if the fields are not empty
			if(StringUtils.isNotEmpty(userName) 
					&& StringUtils.isNotEmpty(password)){
				try {
					File file = new File(Thread.currentThread().getContextClassLoader().getResource(TempUser.class.getName()+".cfg").getFile());
					logger.debug("The file path: {} realtive: {} canonical",file.getAbsolutePath(), file.getPath(), file.getCanonicalPath());
					List<String> lines = new ArrayList<String>();
					lines.add(userName);
					lines.add(password);
					FileUtils.writeLines(file, lines, false);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//empty the file
				try {
					File file = new File(Thread.currentThread().getContextClassLoader().getResource(TempUser.class.getName()+".cfg").getFile());
					List<String> lines = new ArrayList<String>();
					lines.add("");
					FileUtils.writeLines(file, lines, false);
		 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
				// Executor is down start it
			    executor= Executors.newSingleThreadScheduledExecutor();
//				executor= Executors.newScheduledThreadPool(10);
		        Runnable taskThread = runnableImplementationClass;
		        // get the run frequency unit
		        TimeUnit unit = null;
		        switch (MONITOR_FREQUENCY_UNIT) {
				case "DAYS":
					unit = TimeUnit.DAYS;
					break;
				case "MINUTES":
					unit = TimeUnit.MINUTES;
					break;
				case "SECONDS":
					unit = TimeUnit.SECONDS;
					break;					
				default:
					unit = TimeUnit.HOURS;
					break;
				}
		        
		        executor.scheduleAtFixedRate(taskThread, initialDelay, runFrequency, unit);
		        
		        
		        logger.debug("Scheduled the event");				

		        schedule.setStatus(Schedule.SCHEDULED);
			logger.debug("SCHEDULER IS SCHEDULED");
		}
	}
}