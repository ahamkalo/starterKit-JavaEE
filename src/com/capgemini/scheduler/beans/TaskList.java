package com.capgemini.scheduler.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.capgemini.scheduler.entities.Task;
import com.capgemini.scheduler.service.TaskBean;

@SuppressWarnings("serial")
@ManagedBean(name = "TaskList")
@RequestScoped
public class TaskList implements Serializable
{
    @EJB
    private TaskBean taskBean;
    private List<Task> tasks = null;

    public TaskList()
    {
    }

    @PostConstruct
    public void initialize()
    {
        tasks = taskBean.getTaskList();
    }

    public List<Task> getTasks()
    {
        return tasks;
    }
}
