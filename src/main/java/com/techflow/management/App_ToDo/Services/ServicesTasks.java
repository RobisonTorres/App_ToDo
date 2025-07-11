package com.techflow.management.App_ToDo.Services;

import com.techflow.management.App_ToDo.Models.Sector;
import com.techflow.management.App_ToDo.Models.Task;
import com.techflow.management.App_ToDo.Repositories.RepositorySector;
import com.techflow.management.App_ToDo.Repositories.RepositoryTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesTasks {

    /* This service class provides methods to manage tasks.
    It interacts with the RepositoryTask to perform CRUD operations.*/
    private final RepositoryTask repositoryTask;
    private final RepositorySector repositorySector;

    // Constructor to inject the RepositoryTask dependency.
    public ServicesTasks(RepositoryTask repositoryTask, RepositorySector repositorySector) {
        this.repositoryTask = repositoryTask;
        this.repositorySector = repositorySector;
    }

    public Task getTaskById(Integer id) {
        // This function retrieves a task by its ID.
        // If the task is not found, it returns null.
        return repositoryTask.findById(id).orElse(null);
    }

    public List<Task> getAllTask() {
        // This function retrieves all tasks from the repository.
        return repositoryTask.findAll();
    }

    public Task saveTask(Task task) {
        // This function saves a task to the repository.
        return repositoryTask.save(task);
    }

    public void deleteTaskByID(Integer id) {
        // This function deletes a task by its ID.
        repositoryTask.deleteById(id);
    }

    public Sector checkExistingSector(String sector) {
        // This function...
        return repositorySector.findByName(sector);
    }

    public Sector saveSector(Sector sector) {
        // This function...
        return repositorySector.save(sector);
    }
}