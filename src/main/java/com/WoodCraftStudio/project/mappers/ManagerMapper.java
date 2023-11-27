package com.WoodCraftStudio.project.mappers;

import com.WoodCraftStudio.project.dto.ManagerDTO;
import com.WoodCraftStudio.project.entities.Manager;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    Manager toEntity(ManagerDTO managerDTO);

    ManagerDTO toDTO(Manager manager);

    List<ManagerDTO> toDTO(List<Manager> managers);
}
