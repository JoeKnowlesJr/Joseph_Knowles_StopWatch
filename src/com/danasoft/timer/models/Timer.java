package com.danasoft.timer.models;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Timer implements Serializable {
	private static final long serialVersionUID = 1L;
	public Timer() { startTime = System.currentTimeMillis(); endTime = -1L; }
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
	private long startTime;
	private Long endTime;
	public String getStartTime() { return sdf.format(new Date(startTime)); }
	public String getEndTime() { return sdf.format(new Date(endTime));  }
	public boolean isRunning() { return (endTime == -1L); }
	
	public long stop() {
		endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	public String getTotalTime() {
		long delta = endTime - startTime;
		long seconds = delta / 1000;
		long minutes = seconds / 60;
		return String.format("%dm %ds", minutes, seconds);
	}
}