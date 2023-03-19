package com.WoodCraftStudio.project.converters;

import com.WoodCraftStudio.project.dto.ClientDTO;
import com.WoodCraftStudio.project.entities.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientConverter {

    public ClientDTO entityToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(client.getName());
        clientDTO.setInn(client.getInn());
        clientDTO.setPhoneNumber(client.getPhoneNumber());
        clientDTO.setAddresses(client.getAddresses());

        return clientDTO;
    }

    public List<ClientDTO> entityToDTO(List<Client> clients) {
        return clients.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public Client DTOToEntity(ClientDTO clientDTO){
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setInn(clientDTO.getInn());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setAddresses(clientDTO.getAddresses());

        return client;
    }
}
