package com.test.inventory.controller;

import com.test.inventory.domain.ProductDTO;
import com.test.inventory.entities.Product;
import com.test.inventory.entities.ProductArticles;
import com.test.inventory.service.ProductsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductsController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;

    /**
     * Method to test {@link ProductsController#createProducts(ProductDTO)} with list of products
     * @throws Exception
     */
    @Test
    public void testCreateProducts() throws Exception {

        String productsJson = "{\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"name\": \"Dining Chair\",\n" +
                "      \"contain_articles\": [\n" +
                "        {\n" +
                "          \"art_id\": \"1\",\n" +
                "          \"amount_of\": \"4\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"art_id\": \"2\",\n" +
                "          \"amount_of\": \"8\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"art_id\": \"3\",\n" +
                "          \"amount_of\": \"1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dinning Table\",\n" +
                "      \"contain_articles\": [\n" +
                "        {\n" +
                "          \"art_id\": \"1\",\n" +
                "          \"amount_of\": \"4\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"art_id\": \"2\",\n" +
                "          \"amount_of\": \"8\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"art_id\": \"4\",\n" +
                "          \"amount_of\": \"1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";


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
        Mockito.when(productsService.createProducts(Mockito.any())).thenReturn(products);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/inventory/createProducts").accept(MediaType.APPLICATION_JSON_VALUE).content(productsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Assert.assertTrue(response.getContentAsString().contains("table"));
    }

    /**
     * Method to test {@link ProductsController#createProducts(ProductDTO)} with list of products which has improper data
     * @throws Exception
     */
    @Test
    public void testCreateProducts_400BadRequest() throws Exception {

        String productsJson = "{\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"name\": \"Dining Chair\",\n" +
                "      \"contain_articles\": [\n" +
                "        {\n" +
                "          \"art_id\": \"1\",\n" +
                "          \"amount_of\": \"4\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"art_id\": \"sdas\",\n" +
                "          \"amount_of\": \"8\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"art_id\": \"3\",\n" +
                "          \"amount_of\": \"1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Dinning Table\",\n" +
                "      \"contain_articles\": [\n" +
                "        {\n" +
                "          \"art_id\": \"1\",\n" +
                "          \"amount_of\": \"4\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"art_id\": \"2\",\n" +
                "          \"amount_of\": \"8\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"art_id\": \"4\",\n" +
                "          \"amount_of\": \"1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";


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
        Mockito.when(productsService.createProducts(Mockito.any())).thenReturn(products);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/inventory/createProducts").accept(MediaType.APPLICATION_JSON_VALUE).content(productsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assert.assertTrue(response.getContentAsString().contains("table"));
    }

    /**
     * Method to test {@link ProductsController#getProducts()} to retrieve all products
     * @throws Exception
     */
    @Test
    public void testGetProducts() throws Exception {

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
        Mockito.when(productsService.getProducts()).thenReturn(products);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory/products");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].name").isNotEmpty());
    }

    /**
     * Method to test {@link ProductsController#getProduct(int)} to retrieve product
     * @throws Exception
     */
    @Test
    public void testGetProduct() throws Exception {

        Product product = new Product();
        product.setName("table");
        product.setPrice(20);
        ProductArticles productArticles = new ProductArticles();
        productArticles.setAmount_of(2);
        productArticles.setArt_id(1);
        List<ProductArticles> productArticlesList = new ArrayList<>();
        productArticlesList.add(productArticles);
        product.setContain_articles(productArticlesList);
        Mockito.when(productsService.getProduct(Mockito.eq(1))).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory/products/1");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty());
    }

    /**
     * Method to test {@link ProductsController#getProduct(int)} to retrieve product with wrong url
     * @throws Exception
     */
    @Test
    public void testGetProduct_404NotFound() throws Exception {

        Product product = new Product();
        product.setName("table");
        product.setPrice(20);
        ProductArticles productArticles = new ProductArticles();
        productArticles.setAmount_of(2);
        productArticles.setArt_id(1);
        List<ProductArticles> productArticlesList = new ArrayList<>();
        productArticlesList.add(productArticles);
        product.setContain_articles(productArticlesList);
        Mockito.when(productsService.getProduct(Mockito.eq(1))).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory/prod/1");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method to test {@link ProductsController#removeProduct(int)} to remove product
     * @throws Exception
     */
    @Test
    public void testDeleteProduct() throws Exception {

        Product product = new Product();
        product.setName("table");
        product.setPrice(20);
        ProductArticles productArticles = new ProductArticles();
        productArticles.setAmount_of(2);
        productArticles.setArt_id(1);
        List<ProductArticles> productArticlesList = new ArrayList<>();
        productArticlesList.add(productArticles);
        product.setContain_articles(productArticlesList);
        Mockito.when(productsService.deleteProduct(Mockito.eq(1))).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/inventory/products/1");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty());
    }
}
