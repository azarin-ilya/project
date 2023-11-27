package com.WoodCraftStudio.project.repositories;

import com.WoodCraftStudio.project.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
