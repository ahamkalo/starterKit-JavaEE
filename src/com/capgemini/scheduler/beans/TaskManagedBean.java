package com.capgemini.scheduler.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.capgemini.scheduler.entities.Task;
import com.capgemini.scheduler.service.TaskBean;

@SuppressWarnings("serial")
@ManagedBean(name = "TaskManagedBean")
@SessionScoped
public class TaskManagedBean implements Serializable {
	@EJB
	private TaskBean taskBean;
	private Task selectedTask;
	private Task newTask;

	public TaskManagedBean() {
	}

	public Task getNewTask() {
		return newTask;
	}

	public void setNewTask(Task newTask) {
		this.newTask = newTask;
	}

	public Task getSelectedTask() {
		return selectedTask;
	}

	public String setSelectedTask(Task selectedTask) {
		this.selectedTask = taskBean.getUpdatedTask(selectedTask);
		return "EditTask";
	}

	public String goToHomePage() {
		return "TaskList";
	}

	public String goToCreateNewTask() {
		newTask = new Task();
		return "CreateNewTask";
	}

	public String duplicateTask() {
		newTask = selectedTask;
		newTask.setTaskId("<Task ID>");
		return "CreateNewTask";
	}

	public String updateTask() {
		try {
			selectedTask = taskBean.updateTask(selectedTask);
		} catch (Exception ex) {
		}
		return null;
	}

	public String deleteTask() {
		taskBean.deleteTask(selectedTask);
		return "TaskList";
	}

	public String createTask() {
		try {
			selectedTask = taskBean.createTask(newTask);
			return "EditTask";
		} catch (Exception ex) {
		}
		return null;
	}
}
