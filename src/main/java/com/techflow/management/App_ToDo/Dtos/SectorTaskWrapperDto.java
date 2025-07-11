package com.techflow.management.App_ToDo.Dtos;

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