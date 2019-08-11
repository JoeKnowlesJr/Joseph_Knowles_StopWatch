package com.danasoft.stopwatch.models;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class StopWatch implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 5143112303338900199L;

	public StopWatch() { startTime = System.currentTimeMillis(); endTime = -1L; }
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
	private long startTime;
	private long endTime;
	
	public String getStartTime() {
		String retVal = sdf.format(new Date(startTime)); 
		return retVal;
	}
	public String getEndTime() {
		return (endTime == -1) ? "Inactive" : sdf.format(new Date(endTime));  
	}
	public boolean isRunning() { return (endTime == -1L); }
	
	public long stop() {
		endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	public String getElapsedTime() {
		long now = (endTime == -1) ? System.currentTimeMillis(): endTime;
		long delta = now - startTime;
		long seconds = delta / 1000;
		return String.format("%dm %ds", (int)seconds / 60, (int)seconds % 60);
	}
}