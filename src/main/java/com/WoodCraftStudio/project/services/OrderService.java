package com.WoodCraftStudio.project.services;

import com.WoodCraftStudio.project.converters.OrderConverter;
import com.WoodCraftStudio.project.dto.OrderDTO;
import com.WoodCraftStudio.project.entities.Client;
import com.WoodCraftStudio.project.entities.Order;
import com.WoodCraftStudio.project.exception_hadling.ExceptionMessage;
import com.WoodCraftStudio.project.exception_hadling.NotFoundException;
import com.WoodCraftStudio.project.mappers.OrderMapper;
import com.WoodCraftStudio.project.repositories.ClientRepository;
import com.WoodCraftStudio.project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderMapper orderMapper;

    public void save(OrderDTO orderDTO, Integer clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND));
        Order order = orderConverter.DTOToEntity(orderDTO);
        client.addOrder(order);
        clientRepository.save(client);
    }

    public OrderDTO getOrder(Integer id) {
        return orderConverter.EntityToDTO(orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND)));
    }

    public List<OrderDTO> getOrders(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.CLIENT_NOT_FOUND));
        return client.getOrders().stream()
                .map(order -> orderConverter.EntityToDTO(order))
                .collect(Collectors.toList());
    }

    public OrderDTO updateOrder(Integer id, OrderDTO orderDTO) {
        Order updatedOrder = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND));
        orderMapper.updateOrder(orderDTO, updatedOrder);
        return orderConverter.EntityToDTO(orderRepository.save(updatedOrder));
    }

    public void deleteOrder(Integer id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND);
        } else
            orderRepository.deleteById(id);
    }
}
