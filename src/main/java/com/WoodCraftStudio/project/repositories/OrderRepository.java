package com.WoodCraftStudio.project.repositories;

import com.WoodCraftStudio.project.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
