package com.WoodCraftStudio.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
    @SequenceGenerator(name = "orderSequence", sequenceName = "order_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "product_name")
    private String productName;
    private String price;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    public Order(Integer id, LocalDate creationDate, String productName, String  price) {
        this.id = id;
        this.creationDate = creationDate;
        this.productName = productName;
        this.price = price;
    }
}
