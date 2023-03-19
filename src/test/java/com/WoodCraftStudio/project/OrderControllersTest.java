package com.WoodCraftStudio.project;

import com.WoodCraftStudio.project.dto.ClientDTO;
import com.WoodCraftStudio.project.dto.OrderDTO;
import com.WoodCraftStudio.project.entities.Client;
import com.WoodCraftStudio.project.entities.Order;
import com.WoodCraftStudio.project.exception_hadling.NotFoundException;
import com.WoodCraftStudio.project.repositories.ClientRepository;
import com.WoodCraftStudio.project.repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllersTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ClientRepository clientRepository;

    @AfterEach
    public void resetDb() {
        clientRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void addOrderToClient() throws Exception {
        Integer clientId = createTestClient().getId();
        Order order = createTestOrder();

        mockMvc.perform(post("/order/{clientId}", clientId)
                        .content(objectMapper.writeValueAsString(order))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrder() throws Exception {
        Order order = createTestOrder();
        Integer id = order.getId();

        mockMvc.perform(get("/order/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creationDate").value("2023-03-18"))
                .andExpect(jsonPath("$.productName").value("шкаф"))
                .andExpect(jsonPath("$.price").value(149000));
    }

    @Test
    public void getNotExistingOrder() throws Exception {
        mockMvc.perform(get("/order/1"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NotFoundException.class));
    }

    @Test
    public void getAllOrders() throws Exception {
        Order order = createTestOrder();
        Client client = order.getClient();
        client.addOrder(order);
        Integer clientId = client.getId();

        mockMvc.perform(get("/orders/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].creationDate").value("2023-03-18"))
                .andExpect(jsonPath("$[*].productName").value("шкаф"))
                .andExpect(jsonPath("$[*].price").value("149000"));
    }

    @Test
    public void updateOrder() throws Exception {
        Integer id = createTestOrder().getId();

        mockMvc.perform(put("/order/{id}", id)
                        .content(objectMapper.writeValueAsString(new OrderDTO("стол")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("стол"));
    }

    @Test
    public void deleteClient() throws Exception {
        Order order = createTestOrder();

        mockMvc.perform(delete("/order/{id}", order.getId()))
                .andExpect(status().isOk());
    }

    public Client createTestClient() {
        Client client = new Client(1, "ABC", "1234567", "9161112233", new HashSet<>(), new ArrayList<>());

        return clientRepository.save(client);
    }

    public Order createTestOrder() {
        Order order = new Order(1, LocalDate.of(2023, 03, 18), "шкаф", "149000", createTestClient());

        return orderRepository.save(order);
    }
}
