package com.techflow.management.App_ToDo.Controllers;

import com.techflow.management.App_ToDo.Models.Task;
import com.techflow.management.App_ToDo.Services.ServicesTasks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

    private final ServicesTasks servicesTasks;

    public MainController(ServicesTasks servicesTasks) {
        this.servicesTasks = servicesTasks;
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
}