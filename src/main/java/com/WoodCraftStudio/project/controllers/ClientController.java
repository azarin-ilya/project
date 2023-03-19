package com.WoodCraftStudio.project.controllers;

import com.WoodCraftStudio.project.dto.ClientDTO;
import com.WoodCraftStudio.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/client/add")
    public void addClient(@Valid @RequestBody ClientDTO client) {
        clientService.save(client);
    }

    @GetMapping("/client/{id}")
    public ClientDTO getClient(@PathVariable("id") Integer id) {
        return clientService.getClient(id);
    }

    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientService.getClients();
    }

    @PutMapping("/client/{id}")
    public void updateClient(@PathVariable("id") Integer id, @RequestBody ClientDTO client) {
        clientService.updateClient(id, client);
    }

    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable("id") Integer id) {
        clientService.deleteClient(id);
    }
}
