package com.test.inventory.service;

import com.test.inventory.entities.Product;

import java.util.List;

/**
 * Interface for products related functional operations
 */
public interface ProductsService {

    /**
     * Interface to store products which received from request
     * @param products the records which needs to be validated
     * @return validation result with message and error records
     */
    List<Product> createProducts(List<Product> products);

    /**
     * Interface method to retrieve products from inventory system
     * @return retrieved products with it's details
     */
    List<Product> getProducts();

    /**
     * Get requested product details
     * @param id the id of product which requested
     * @return requested product
     */
    Product getProduct(int id);

    /**
     * Remove product from database based on id
     * @param id the product id
     * @return deleted product
     */
    Product deleteProduct(int id);

}
