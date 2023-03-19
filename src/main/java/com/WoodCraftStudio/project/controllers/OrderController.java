package com.WoodCraftStudio.project.controllers;

import com.WoodCraftStudio.project.dto.OrderDTO;
import com.WoodCraftStudio.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/{clientId}")
    public void addOrderToClient(@Valid @RequestBody OrderDTO orderDTO
            , @PathVariable("clientId") Integer clientId) {
       orderService.save(orderDTO, clientId);
    }

    @GetMapping("/order/{id}")
    public OrderDTO getOrder(@PathVariable("id") Integer id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/orders/{clientId}")
    public List<OrderDTO> getOrders(@PathVariable("clientId") Integer id){
        return orderService.getOrders(id);
    }

    @PutMapping("/order/{id}")
    public OrderDTO updateOrder(@PathVariable("id") Integer id, @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(id, orderDTO);
    }

    @DeleteMapping("order/{id}")
    public void deleteOrder(@PathVariable("id")Integer id){
        orderService.deleteOrder(id);
    }
}
