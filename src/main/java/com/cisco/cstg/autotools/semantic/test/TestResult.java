package com.cisco.cstg.autotools.semantic.test;

import java.util.List;

public class TestResult {
	int runCount;
	int failureCount;
	long runTime;
	List<Failure> failures;
	
	public int getRunCount() {
		return runCount;
	}
	public void setRunCount(int runCount) {
		this.runCount = runCount;
	}
	public int getFailureCount() {
		return failureCount;
	}
	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}
	public long getRunTime() {
		return runTime;
	}
	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}
	public List<Failure> getFailures() {
		return failures;
	}
	public void setFailures(List<Failure> failures) {
		this.failures = failures;
	}
}