package com.test.inventory.repository;

import com.test.inventory.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;

/**
 *Repository class to handle all database operations for products
 */
@Transactional(rollbackFor = SQLDataException.class)
@Repository
public interface ProductsRepository extends CrudRepository<Product, Integer> {

}
