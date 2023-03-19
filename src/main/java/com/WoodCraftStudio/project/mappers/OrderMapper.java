package com.WoodCraftStudio.project.mappers;

import com.WoodCraftStudio.project.dto.OrderDTO;
import com.WoodCraftStudio.project.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface OrderMapper {
    void updateOrder(OrderDTO requestOrder, @MappingTarget Order responseOrder);

}