package com.WoodCraftStudio.project.repositories;

import com.WoodCraftStudio.project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
