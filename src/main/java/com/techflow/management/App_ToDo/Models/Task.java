package com.techflow.management.App_ToDo.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date date;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private String priority;

    public Task(Integer id, String name, Date date, TaskStatus status, String priority) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.status = status;
        this.priority = priority;
    }

    public Task() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public enum TaskStatus {
        IN_PROGRESS, COMPLETED, SUSPENDED
    }
}