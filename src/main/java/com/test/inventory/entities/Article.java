package com.test.inventory.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inventories")
@Data
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "art_id", nullable = false)
    private int art_id;
    @Column(name = "name")
    private String name;
    @Column(name = "stock")
    private int stock;
}
