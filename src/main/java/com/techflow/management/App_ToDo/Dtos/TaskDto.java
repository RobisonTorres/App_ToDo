package com.techflow.management.App_ToDo.Dtos;

import java.time.LocalDate;

/*TaskDto is a Data Transfer Object (DTO) that represents a task in the application.
It includes fields for the task's name, date, status, and priority. */
public class TaskDto {

    private String name;
    private LocalDate date;
    private String status;
    private String priority;
    private Integer sector_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getSector_id() {
        return sector_id;
    }

    public void setSector_id(Integer sector_id) {
        this.sector_id = sector_id;
    }
}