package com.test.inventory.service.implementation;

import com.test.inventory.entities.Article;
import com.test.inventory.repository.ArticleRepository;
import com.test.inventory.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to do all article related funtionalities
 */
@Service
public class ArticleServiceImplementation implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> createArticles(List<Article> articles) {

        return (List<Article>) articleRepository.saveAll(articles);
    }

    @Override
    public List<Article> getArticles() {

        return (ArrayList)articleRepository.findAll();
    }

    @Override
    public void updateArticlesStock(int art_id, int stock) {
        articleRepository.updateArticleStock(art_id, stock);
    }

}
