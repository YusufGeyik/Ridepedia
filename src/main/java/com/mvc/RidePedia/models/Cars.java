package com.mvc.RidePedia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private String brand;
    private int productionYear;
    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Reviews> reviewsList = new ArrayList<>();
}

