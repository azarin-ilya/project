package com.WoodCraftStudio.project.mappers;

import com.WoodCraftStudio.project.dto.ClientDTO;
import com.WoodCraftStudio.project.entities.Client;
import org.mapstruct.*;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface ClientMapper {
    void updateClient(ClientDTO requestClient, @MappingTarget Client responseClient);
}
