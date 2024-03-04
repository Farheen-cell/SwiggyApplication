package com.masai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deliverypartners")
public class DeliveryPartner {

    private Integer deliveryId;
    private String deliveryName;
    private String address;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    private List<Orders> orders= new ArrayList<>();

    @ManyToMany(mappedBy = "restaurants")
    private List<Restaurant> restaurants = new ArrayList<>();

}
