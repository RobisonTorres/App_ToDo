package com.techflow.management.App_ToDo.Repositories;

import com.techflow.management.App_ToDo.Models.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorySector extends JpaRepository<Sector, Integer> {

    Sector findByName(String name);
}