package com.test.inventory.service;

import com.test.inventory.entities.Product;
import com.test.inventory.entities.ProductArticles;
import com.test.inventory.repository.ProductsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductsService productsService;

    @MockBean
    private ProductsRepository productsRepository;

    /**
     * Method to test {@link com.test.inventory.service.implementation.ProductsServiceImplementation#createProducts(List)} to store all products
     * @throws Exception
     */
    @Test
    public void testCreateProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("table");
        product.setPrice(20);
        ProductArticles productArticles = new ProductArticles();
        productArticles.setAmount_of(2);
        productArticles.setArt_id(1);
        List<ProductArticles> productArticlesList = new ArrayList<>();
        productArticlesList.add(productArticles);
        product.setContain_articles(productArticlesList);
        products.add(product);
        Mockito.when(productsRepository.saveAll(Mockito.anyIterable())).thenReturn(products);
        List<Product> productsList = productsService.createProducts(products);
        Assert.assertEquals("table", productsList.get(0).getName());
    }

    /**
     * Method to test {@link com.test.inventory.service.implementation.ProductsServiceImplementation#getProduct(int)} to retrieve all products
     * @throws Exception
     */
    @Test
    public void testGetProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("table");
        product.setPrice(20);
        ProductArticles productArticles = new ProductArticles();
        productArticles.setAmount_of(2);
        productArticles.setArt_id(1);
        List<ProductArticles> productArticlesList = new ArrayList<>();
        productArticlesList.add(productArticles);
        product.setContain_articles(productArticlesList);
        products.add(product);
        Mockito.when(productsRepository.findAll()).thenReturn(products);
        List<Product> productsList = productsService.getProducts();
        Assert.assertEquals("table", productsList.get(0).getName());
    }

    /**
     * Method to test {@link com.test.inventory.service.implementation.ProductsServiceImplementation#getProduct(int)} to get product based on id
     * @throws Exception
     */
    @Test
    public void testGetProduct() {
        Product product = new Product();
        product.setName("table");
        product.setPrice(20);
        ProductArticles productArticles = new ProductArticles();
        productArticles.setAmount_of(2);
        productArticles.setArt_id(1);
        List<ProductArticles> productArticlesList = new ArrayList<>();
        productArticlesList.add(productArticles);
        product.setContain_articles(productArticlesList);
        Mockito.when(productsRepository.findById(Mockito.any())).thenReturn(Optional.of(product));
        Product serviceProduct = productsService.getProduct(1);
        Assert.assertEquals("table", serviceProduct.getName());
    }

    /**
     * Method to test {@link com.test.inventory.service.implementation.ProductsServiceImplementation#deleteProduct(int)} to delete product based on id
     * @throws Exception
     */
    @Test
    public void testRemoveProduct() {
        Product product = new Product();
        product.setName("table");
        product.setPrice(20);
        ProductArticles productArticles = new ProductArticles();
        productArticles.setAmount_of(2);
        productArticles.setArt_id(1);
        List<ProductArticles> productArticlesList = new ArrayList<>();
        productArticlesList.add(productArticles);
        product.setContain_articles(productArticlesList);
        Mockito.when(productsRepository.findById(1)).thenReturn(Optional.of(product));
        Product serviceProduct = productsService.deleteProduct(1);
        Assert.assertEquals("table", serviceProduct.getName());
    }
}
