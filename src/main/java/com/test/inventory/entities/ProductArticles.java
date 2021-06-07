package com.test.inventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_articles")
@Data
public class ProductArticles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount_of")
    private int amount_of;

    @Column(name = "art_id")
    private int art_id;

    /*@OneToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Article article;*/

    @ManyToOne
    @JsonIgnore
    private Product product;
}
