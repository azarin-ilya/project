package com.WoodCraftStudio.project.dto;

import com.WoodCraftStudio.project.entities.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ManagerDTO {
    private Integer id;
    private String name;
    private String surname;
}
