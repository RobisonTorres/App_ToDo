package com.techflow.management.App_ToDo.Services;

import com.techflow.management.App_ToDo.Models.Task;
import com.techflow.management.App_ToDo.Repositories.RepositoryTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesTasks {

    private final RepositoryTask repositoryTask;

    public ServicesTasks(RepositoryTask repositoryTask) {
        this.repositoryTask = repositoryTask;
    }

    public Task getTaskById(Integer id) {
        return repositoryTask.findById(id).orElse(null);
    }

    public List<Task> getAllTask() {
        return repositoryTask.findAll();
    }
}