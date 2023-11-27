package com.WoodCraftStudio.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "managerSequence")
    @SequenceGenerator(name = "managerSequence", sequenceName = "managers_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String surname;


}
