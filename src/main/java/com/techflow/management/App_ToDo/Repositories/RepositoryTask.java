package com.techflow.management.App_ToDo.Repositories;

import com.techflow.management.App_ToDo.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTask extends JpaRepository<Task, Integer> {

}