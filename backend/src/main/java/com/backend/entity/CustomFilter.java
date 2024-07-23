package com.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "custom_filter")
@Data
public class CustomFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "filter", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Criterion> criteria = new ArrayList<>();

}
