package com.capgemini.scheduler.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import com.capgemini.scheduler.entities.Task;

@Stateless
@LocalBean
public class TaskBean {
	@Resource
	TimerService timerService;
	static Logger logger = Logger.getLogger("TaskBean");

	@Timeout
	public void timeout(Timer timer) {
		try {
			TaskScheduler batchJob = new TaskScheduler();
			batchJob.executeTask(timer); // Asynchronous method
		} catch (Exception ex1) {
			logger.severe("Exception caught: " + ex1);
		}
	}

	private Timer getTimer(Task task) {
		Collection<Timer> timers = timerService.getTimers();
		for (Timer timer : timers) {
			if (task.equals((Task) timer.getInfo())) {
				return timer;
			}
		}
		return null;
	}

	public Task createTask(Task task) throws Exception {
		// if (getTimer(task) != null) {
		// throw new DuplicateKeyException("Job with the ID already exist!");
		// }

		TimerConfig timerConfig = new TimerConfig(task, true);

		ScheduleExpression scheduleExpression = new ScheduleExpression();
		scheduleExpression.start(task.getStartDate());
		scheduleExpression.end(task.getEndDate());
		scheduleExpression.second(task.getSecond());
		scheduleExpression.minute(task.getMinute());
		scheduleExpression.hour(task.getHour());
		scheduleExpression.dayOfMonth(task.getDayOfMonth());
		scheduleExpression.month(task.getMonth());
		scheduleExpression.year(task.getYear());
		scheduleExpression.dayOfWeek(task.getDayOfWeek());

		Timer newTimer = timerService.createCalendarTimer(scheduleExpression,
				timerConfig);
		task.setNextTimeout(newTimer.getNextTimeout());

		return task;
	}

	public List<Task> getTaskList() {
		logger.info("getTaskList called!");
		ArrayList<Task> tasks = new ArrayList<Task>();
		Collection<Timer> timers = timerService.getTimers();
		for (Timer timer : timers) {
			Task task = (Task) timer.getInfo();
			task.setNextTimeout(timer.getNextTimeout());
			tasks.add(task);
		}
		return tasks;
	}

	public Task getUpdatedTask(Task task) {
		Timer timer = getTimer(task);
		if (timer != null) {
			Task updatedTask = (Task) timer.getInfo();
			updatedTask.setNextTimeout(timer.getNextTimeout());
			return updatedTask;
		}
		return null;
	}

	public Task updateTask(Task task) throws Exception {
		Timer timer = getTimer(task);
		if (timer != null) {
			timer.cancel();
			return createTask(task);
		}
		return null;
	}

	public void deleteTask(Task task) {
		Timer timer = getTimer(task);
		if (timer != null) {
			timer.cancel();
		}
	}
}