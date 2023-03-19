package com.WoodCraftStudio.project.converters;

import com.WoodCraftStudio.project.dto.OrderDTO;
import com.WoodCraftStudio.project.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    public Order DTOToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setProductName(orderDTO.getProductName());
        order.setPrice(orderDTO.getPrice());
        order.setCreationDate(orderDTO.getCreationDate());

        return order;
    }

    public OrderDTO EntityToDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductName(order.getProductName());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setCreationDate(order.getCreationDate());

        return orderDTO;
    }
}
