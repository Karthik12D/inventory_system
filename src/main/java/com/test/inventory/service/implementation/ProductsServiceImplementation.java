package com.test.inventory.service.implementation;

import com.test.inventory.entities.Product;
import com.test.inventory.entities.ProductArticles;
import com.test.inventory.repository.ProductsRepository;
import com.test.inventory.service.ArticleService;
import com.test.inventory.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation class for Products ServiceInterface
 */
@Service
public class ProductsServiceImplementation implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ArticleService articleService;


    @Override
    public List<Product> createProducts(List<com.test.inventory.entities.Product> products) {
        return (List<Product>) productsRepository.saveAll(products);
    }

    @Override
    public List<com.test.inventory.entities.Product> getProducts() {
       List<com.test.inventory.entities.Product> productEntities = (ArrayList) productsRepository.findAll();
        return productEntities;
    }

    @Override
    public Product getProduct(int id) {

        return productsRepository.findById(id).get();
    }

    @Override
    public Product deleteProduct(int id) {

        Optional product = productsRepository.findById(id);
        if (product.isPresent()) {
            for (ProductArticles productArticle: ((Product)product.get()).getContain_articles()) {
                articleService.updateArticlesStock(productArticle.getArt_id(), productArticle.getAmount_of());
            }
            productsRepository.deleteById(id);
            return (Product) product.get();

        } else {
            return null;
        }
    }

}
