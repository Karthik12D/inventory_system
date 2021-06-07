package com.test.inventory;

import com.test.inventory.domain.ProductDTO;
import com.test.inventory.entities.Product;
import com.test.inventory.entities.ProductArticles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Integration test class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InventoryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testCreateProducts() {

        ProductDTO productDTO = new ProductDTO();
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
        productDTO.setProducts(products);
        HttpEntity<ProductDTO> entity = new HttpEntity<>(productDTO, new HttpHeaders());
        ResponseEntity<Object> responseEntity = restTemplate.exchange(createURLWithPort("/inventory/createProducts"), HttpMethod.POST, entity , Object.class);
        Assert.assertEquals(responseEntity.getHeaders().getContentType(), MediaType.APPLICATION_JSON);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testGetProducts() {

        ResponseEntity<Object> responseEntity = restTemplate.exchange(createURLWithPort("/inventory/products"), HttpMethod.GET, null, Object.class);
        Assert.assertEquals(responseEntity.getHeaders().getContentType(), MediaType.APPLICATION_JSON);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
