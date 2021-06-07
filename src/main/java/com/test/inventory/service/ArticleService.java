package com.test.inventory.service;

import com.test.inventory.entities.Article;

import java.util.List;

/**
 * Service to do all article related functional operations
 */
public interface ArticleService {

    /**
     * Store articles into inventory system
     * @param articles the articles from request
     */
    List<Article> createArticles(List<Article> articles);

    /**
     * Get available articles with it's details from inventory system
     * @return articles
     */
    List<Article> getArticles();

    /**
     * Update article with stock based on article id.
     * @param art_id the article id
     * @param stock the amount of stock which needs to be reduced
     */
    void updateArticlesStock(int art_id, int stock);
}
