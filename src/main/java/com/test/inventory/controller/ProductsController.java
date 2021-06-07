package com.test.inventory.controller;

import com.test.inventory.domain.ProductDTO;
import com.test.inventory.entities.Product;
import com.test.inventory.service.ProductsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Products Controller will be a web layer for all product related operations
 */
@RestController
@RequestMapping("/inventory")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    /**
     * Method to store products from request and will return response with success status
     * @param productDTO the products from request
     * @return response with stored product and http status code
     */
    @PostMapping(value = "/createProducts", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Create Products", notes = "To store all products with contained article details in database")
    public ResponseEntity<Object> createProducts(@RequestBody @ApiParam(value = "Product details with it's articles") ProductDTO productDTO) {
        List<Product> products = productsService.createProducts(productDTO.getProducts());
        return ResponseEntity.status(HttpStatus.CREATED).body(products);
    }

    /**
     * Get products from inventory system
     * @return products
     */
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Get Products", notes = "Get All product details from inventory system")
    public ResponseEntity<Object> getProducts() {
        List<com.test.inventory.entities.Product> products = productsService.getProducts();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProducts(products);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    /**
     * Get product for requested id
     * @param id of the product requested
     * @return product
     */
    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Get Product", notes = "Get product based on product id")
    public ResponseEntity<Object> getProduct(@PathVariable @ApiParam(value = "The id of the product which requested") int id) {
        Product product = productsService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    /**
     * Remove product from inventory system using id
     * @param id the id of the product which needs to be removed
     * @return the product
     */
    @DeleteMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Delete product from inventory system once it is sold out")
    public ResponseEntity<Object> removeProduct(@PathVariable @ApiParam(value = "The id of the product which needs to be deleted. Ex:1") int id) {
        Product product = productsService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
