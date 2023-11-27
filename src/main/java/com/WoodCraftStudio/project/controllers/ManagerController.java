package com.WoodCraftStudio.project.controllers;

import com.WoodCraftStudio.project.dto.ManagerDTO;
import com.WoodCraftStudio.project.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/manager/add")
    public void addManager(@RequestBody ManagerDTO managerDTO) {
        managerService.save(managerDTO);
    }

    @GetMapping("/manager/{id}")
    public ManagerDTO getManager(@PathVariable("id") Integer id) {
        return managerService.getManager(id);
    }

    @GetMapping("/managers")
    public List<ManagerDTO> getManagers(){
       return managerService.getManagers();
    }

    @DeleteMapping("/manager/{id}")
    public void deleteManager(@PathVariable("id") Integer id){
        managerService.deleteManager(id);
    }
}
