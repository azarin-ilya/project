package com.WoodCraftStudio.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(name = "region_code")
    private String regionCode;
    @Column(name = "name_of_district")
    private String nameOfDistrict;
    @Column(name = "name_of_settlement")
    private String nameOfSettlement;
    private String street;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "hull_number")
    private String hullNumber;
    @Column(name = "room_number")
    private String roomNumber;

}
