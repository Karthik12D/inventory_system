package com.test.inventory.repository;

import com.test.inventory.entities.Product;
import com.test.inventory.entities.ProductArticles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class to test Product Repositories
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductsRepository productsRepository;

    /**
     * Test JpaRepositories methods which used in {@link ProductsRepository}
     */
    @Test
    public void testRepository() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("table");
        product.setPrice(20);

        Product product1 = new Product();
        product1.setName("Chair");
        product1.setPrice(20);

        ProductArticles productArticles = new ProductArticles();
        productArticles.setAmount_of(2);
        productArticles.setArt_id(1);

        ProductArticles productArticles1 = new ProductArticles();
        productArticles1.setAmount_of(2);
        productArticles1.setArt_id(1);

        List<ProductArticles> productArticlesList = new ArrayList<>();
        productArticlesList.add(productArticles);
        List<ProductArticles> productArticlesList1 = new ArrayList<>();
        productArticlesList1.add(productArticles1);

        product.setContain_articles(productArticlesList);
        product1.setContain_articles(productArticlesList1);
        products.add(product);
        products.add(product1);

        List<Product> productEntities = (List<Product>) productsRepository.saveAll(products);
        Assert.assertEquals("table", productEntities.get(0).getName());

        List<Product> productEntities1 = (List<Product>) productsRepository.findAll();
        Assert.assertEquals("table", productEntities1.get(0).getName());

        Optional productEntity = productsRepository.findById(1);
        Assert.assertTrue(productEntity.isPresent());

        productsRepository.deleteById(1);
    }


}
