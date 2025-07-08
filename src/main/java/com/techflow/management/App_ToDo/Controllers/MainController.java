package com.techflow.management.App_ToDo.Controllers;

import com.techflow.management.App_ToDo.Dtos.TaskDto;
import com.techflow.management.App_ToDo.Models.Task;
import com.techflow.management.App_ToDo.Services.ServicesTasks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.modelmapper.ModelMapper;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

    private final ServicesTasks servicesTasks;
    private final ModelMapper modelMapper;

    public MainController(ServicesTasks servicesTasks, ModelMapper modelMapper) {
        this.servicesTasks = servicesTasks;
        this.modelMapper = modelMapper;
    }

    @GetMapping("get_task/{id}")
    public Task getById(@PathVariable Integer id){

        Task task = servicesTasks.getTaskById(id);
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return task;
    }

    @GetMapping("get_tasks")
    public List<Task> getAllTasks() {

        return servicesTasks.getAllTask();
    }

    @PostMapping("create_task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        servicesTasks.saveTask(task);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update_task")
    public ResponseEntity<Task> updateTask(@RequestBody TaskDto taskDto, @PathVariable Integer id) {
        Task task = servicesTasks.getTaskById(id);
        modelMapper.map(taskDto, task);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete_task/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {
        servicesTasks.deleteTaskByID(id);
        return ResponseEntity.ok().build();
    }
}