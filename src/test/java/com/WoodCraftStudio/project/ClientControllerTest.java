package com.WoodCraftStudio.project;


import com.WoodCraftStudio.project.dto.ClientDTO;
import com.WoodCraftStudio.project.entities.Address;
import com.WoodCraftStudio.project.entities.Client;
import com.WoodCraftStudio.project.entities.Order;
import com.WoodCraftStudio.project.exception_hadling.NotFoundException;
import com.WoodCraftStudio.project.repositories.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {


    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void resetDb() {
        clientRepository.deleteAll();
    }

    @Test
    public void createClient() throws Exception {
        Client client = createTestClient();

        mockMvc.perform(post("/client/add")
                        .content(objectMapper.writeValueAsString(client))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getClient() throws Exception {
        Client client = createTestClient();
        Integer id = client.getId();

        mockMvc.perform(get("/client/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ABC"))
                .andExpect(jsonPath("$.inn").value("1234567891"))
                .andExpect(jsonPath("$.phoneNumber").value("9161112233"))
                .andExpect(jsonPath("$.addresses").exists());
    }

    @Test
    public void getNotExistingClient() throws Exception {
        mockMvc.perform(get("/client/1"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NotFoundException.class));
    }

    @Test
    public void updateClient() throws Exception {
        Integer id = createTestClient().getId();

        mockMvc.perform(put("/client/{id}", id)
                        .content(objectMapper.writeValueAsString(new ClientDTO("DDD")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllClients() throws Exception {
        Client c1 = createTestClient();

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value("ABC"))
                .andExpect(jsonPath("$[*].inn").value("1234567891"))
                .andExpect(jsonPath("$[*].phoneNumber").value("9161112233"))
                .andExpect(jsonPath("$[*].addresses").exists());
    }

    @Test
    public void deleteClient() throws Exception {
        Client client = createTestClient();

        mockMvc.perform(delete("/client/{id}", client.getId()))
                .andExpect(status().isOk());
    }

    private Client createTestClient() {
        Set<Address> addresses = new HashSet<Address>();
        Address address = new Address("22", "Moscow"
                , "Moscow", "Pokrovka", "1", "B", "5");
        addresses.add(address);

        List<Order> orders = new ArrayList<>();
        Order order = new Order(1, LocalDate.now(), "шкаф", "149000");
        orders.add(order);

        Client client = new Client(1, "ABC", "1234567891", "9161112233", addresses, orders);

        return clientRepository.save(client);
    }

}
