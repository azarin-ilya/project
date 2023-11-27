package com.WoodCraftStudio.project.services;

import com.WoodCraftStudio.project.dto.ManagerDTO;
import com.WoodCraftStudio.project.exception_hadling.ExceptionMessage;
import com.WoodCraftStudio.project.exception_hadling.NotFoundException;
import com.WoodCraftStudio.project.mappers.ManagerMapper;
import com.WoodCraftStudio.project.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ManagerMapper managerMapper;

    public void save(ManagerDTO managerDTO) {
        managerRepository.save(managerMapper.toEntity(managerDTO));
    }

    public ManagerDTO getManager(Integer id) {
        return managerMapper.toDTO(managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.MANAGER_NOT_FOUND)));
    }

    public List<ManagerDTO> getManagers() {
        List<ManagerDTO> managers = managerMapper.toDTO(managerRepository.findAll());

        if (managers.isEmpty()) {
            throw new NotFoundException(ExceptionMessage.MANAGER_NOT_FOUND);
        } else return managers;
    }

    public void deleteManager(Integer id) {
        if (managerRepository.findById(id).isEmpty()) {
            throw new NotFoundException(ExceptionMessage.MANAGER_NOT_FOUND);
        } else managerRepository.deleteById(id);
    }
}
