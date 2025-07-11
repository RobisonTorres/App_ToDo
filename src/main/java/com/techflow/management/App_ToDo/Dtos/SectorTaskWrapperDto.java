package com.techflow.management.App_ToDo.Dtos;

// This class is used to wrap Sector and Task DTOs together
// to facilitate operations that involve both entities.
public class SectorTaskWrapperDto {

    private SectorDto sectorDto;
    private TaskDto taskDto;

    public SectorDto getSectorDto() {
        return sectorDto;
    }

    public void setSectorDto(SectorDto sectorDto) {
        this.sectorDto = sectorDto;
    }

    public TaskDto getTaskDto() {
        return taskDto;
    }

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
    }
}