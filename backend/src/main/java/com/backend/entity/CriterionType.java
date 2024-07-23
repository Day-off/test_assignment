package com.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "criterion_type")
@Data
public class CriterionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
