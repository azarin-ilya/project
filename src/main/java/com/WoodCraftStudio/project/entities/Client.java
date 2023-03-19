package com.WoodCraftStudio.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientSequence")
    @SequenceGenerator(name = "clientSequence", sequenceName = "client_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String inn;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ElementCollection
    @CollectionTable(name = "client_addresses", joinColumns = @JoinColumn(name = "client_id"))
    private Set<Address> addresses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Order> orders;

    public Client() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setClient(this);
    }
}
