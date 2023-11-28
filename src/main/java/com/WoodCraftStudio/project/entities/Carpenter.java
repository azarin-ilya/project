package com.WoodCraftStudio.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@Table(name = "carpenters")
public class Carpenter {

    private Integer id;
    private String name;
    private String surname;

}
