package com.capgemini.scheduler.entities;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@SuppressWarnings("serial")
@Stateless
@LocalBean
public class Task implements Serializable {
	private String taskId;
	private String taskName;
	private String description;
	private int progress;
	private int durationTime;

	private Date startDate;
	private Date endDate;
	private String second;
	private String minute;
	private String hour;
	private String dayOfWeek;
	private String dayOfMonth;
	private String month;
	private String year;

	private Date nextTimeout;

	public Task() {
		this.taskId = "";
		this.taskName = "";
		this.description = "";

		this.startDate = new Date();
		this.endDate = null;
		this.second = "0";
		this.minute = "*";
		this.hour = "*";
		this.dayOfMonth = "*";
		this.month = "*";
		this.year = "*";
		this.dayOfWeek = "*";
		this.progress = 0;
		this.durationTime = 0;
	}

	public String getExpression() {
		return "sec=" + second + ";min=" + minute + ";hour=" + hour
				+ ";dayOfMonth=" + dayOfMonth + ";month=" + month + ";year="
				+ year + ";dayOfWeek=" + dayOfWeek;
	}

	@Override
	public boolean equals(Object anotherObj) {
		if (anotherObj instanceof Task) {
			return taskId.equals(((Task) anotherObj).taskId);
		}
		return false;
	}

	@Override
	public String toString() {
		return taskId + "-" + taskName + "-";// + jobClassName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getNextTimeout() {
		return nextTimeout;
	}

	public void setNextTimeout(Date nextTimeout) {
		this.nextTimeout = nextTimeout;
	}
}
