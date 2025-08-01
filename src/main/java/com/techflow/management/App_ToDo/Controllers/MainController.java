package com.techflow.management.App_ToDo.Controllers;

import com.techflow.management.App_ToDo.Dtos.SectorDto;
import com.techflow.management.App_ToDo.Dtos.SectorTaskWrapperDto;
import com.techflow.management.App_ToDo.Dtos.TaskDto;
import com.techflow.management.App_ToDo.Models.Sector;
import com.techflow.management.App_ToDo.Models.Task;
import com.techflow.management.App_ToDo.Services.ServicesTasks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.modelmapper.ModelMapper;

import java.util.List;

// This controller handles HTTP requests related to tasks.
// It provides endpoints to create, read, update, and delete tasks.
@RestController
@CrossOrigin(origins = "*")
public class MainController {

    private final ServicesTasks servicesTasks;
    private final ModelMapper modelMapper;

    // Constructor to inject the ServicesTasks and ModelMapper dependencies.
    // This allows the controller to use the services for task management and model mapping.
    public MainController(ServicesTasks servicesTasks, ModelMapper modelMapper) {
        this.servicesTasks = servicesTasks;
        this.modelMapper = modelMapper;
    }

    @GetMapping("get_task/{id}")
    public Task getById(@PathVariable Integer id){
        // This method retrieves a task by its ID.
        // If the task is not found, it throws a 404 Not Found exception.
        Task task = servicesTasks.getTaskById(id);
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return task;
    }

    @GetMapping("get_tasks")
    public List<Task> getAllTasks() {
        // This method retrieves all tasks from the service.
        return servicesTasks.getAllTask();
    }

    @PostMapping("create_task")
    public ResponseEntity<Task> createTask(@RequestBody SectorTaskWrapperDto sectorTaskWrapperDto) {
        // This method creates a new task from the provided TaskDto.
        // It maps the DTO to a Task entity and saves it using the service.
        TaskDto taskDto = sectorTaskWrapperDto.getTaskDto();
        Task task = modelMapper.map(taskDto, Task.class);

        SectorDto sectorDto = sectorTaskWrapperDto.getSectorDto();
        Sector sector = modelMapper.map(sectorDto, Sector.class);

        // Check if the sector already exists in the database.
        // If it does, associate the existing sector with the task; otherwise, save the new sector.
        Sector checkSector = servicesTasks.checkExistingSector(sector.getName());
        task.setSector(checkSector == null ? sector : checkSector);
        if (checkSector == null) servicesTasks.saveSector(sector);

        servicesTasks.saveTask(task);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update_task/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody SectorTaskWrapperDto sectorTaskWrapperDto, @PathVariable Integer id) {
        // This method updates an existing task with the provided ID.
        // It maps the TaskDto to an existing Task entity and saves it.
        Task task = servicesTasks.getTaskById(id);
        TaskDto taskDto = sectorTaskWrapperDto.getTaskDto();
        modelMapper.map(taskDto, task);

        SectorDto sectorDto = sectorTaskWrapperDto.getSectorDto();
        Sector sector = modelMapper.map(sectorDto, Sector.class);

        Sector checkSector = servicesTasks.checkExistingSector(sector.getName());
        task.setSector(checkSector == null ? sector : checkSector);
        if (checkSector == null) servicesTasks.saveSector(sector);

        servicesTasks.saveTask(task);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete_task/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {
        // This method deletes a task by its ID.
        servicesTasks.deleteTaskByID(id);
        return ResponseEntity.ok().build();
    }
}