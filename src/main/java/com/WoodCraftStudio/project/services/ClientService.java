package com.WoodCraftStudio.project.services;

import com.WoodCraftStudio.project.converters.ClientConverter;
import com.WoodCraftStudio.project.dto.ClientDTO;
import com.WoodCraftStudio.project.entities.Client;
import com.WoodCraftStudio.project.exception_hadling.ExceptionMessage;
import com.WoodCraftStudio.project.exception_hadling.NotFoundException;
import com.WoodCraftStudio.project.mappers.ClientMapper;
import com.WoodCraftStudio.project.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientConverter clientConverter;
    @Autowired
    protected ClientMapper clientMapper;

    public void save(ClientDTO client) {
        clientRepository.save(clientConverter.DTOToEntity(client));
    }

    public ClientDTO getClient(Integer id) {
        return clientConverter.entityToDTO(clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.CLIENT_NOT_FOUND)));
    }

    public List<ClientDTO> getClients() {
        List<ClientDTO> clients = clientConverter.entityToDTO(clientRepository.findAll());
        if (clients.isEmpty()) {
            throw new NotFoundException(ExceptionMessage.CLIENT_NOT_FOUND);
        } else return clients;
    }

    public void updateClient(Integer id, ClientDTO client) {
        Client updatedClient = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.CLIENT_NOT_FOUND));
        clientMapper.updateClient(client, updatedClient);
        clientRepository.save(updatedClient);
    }

    public void deleteClient(Integer id) {
        if (clientRepository.findById(id).isEmpty()) {
            throw new NotFoundException(ExceptionMessage.CLIENT_NOT_FOUND);
        } else
            clientRepository.deleteById(id);
    }
}
