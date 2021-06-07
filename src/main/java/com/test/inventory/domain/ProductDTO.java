package com.test.inventory.domain;

import com.test.inventory.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    List<Product> products;
}
