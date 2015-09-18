package com.capgemini.scheduler.service;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timer;

import com.capgemini.scheduler.entities.Task;

@Stateless
@LocalBean
public class TaskScheduler {
	static Logger logger = Logger.getLogger("TaskScheduler");

	@Asynchronous
	public void executeTask(Timer timer) {
		Task task = (Task) timer.getInfo();
		logger.info("Start of " + task.getTaskName() + " at " + new Date()
				+ "...");

		try {
			logger.info("Running task: " + task);
			for (int i = 0; i < task.getDurationTime(); i++) {
				Thread.sleep(1000);
				actualizeTaskProgress(task, i);
			}
			task.setProgress(0);
		} catch (InterruptedException ex) {
		}
		logger.info("End of " + task.getTaskName() + " at " + new Date());
	}

	private void actualizeTaskProgress(Task task, int i) {
		Double progress = (i + 1.0) / task.getDurationTime() * 100;
		task.setProgress(progress.intValue());
	}
}
