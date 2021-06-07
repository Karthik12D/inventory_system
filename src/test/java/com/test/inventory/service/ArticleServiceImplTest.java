package com.test.inventory.service;

import com.test.inventory.entities.Article;
import com.test.inventory.repository.ArticleRepository;
import com.test.inventory.service.implementation.ArticleServiceImplementation;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @MockBean
    private ArticleRepository articleRepository;
    /**
     * Method to test {@link com.test.inventory.service.implementation.ArticleServiceImplementation#createArticles(List)} to store all articles
     * @throws Exception
     */
    @Test
    public void testCreateArticles() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setName("leg");
        article.setArt_id(1);
        article.setStock(40);
        articles.add(article);
        Mockito.when(articleRepository.saveAll(Mockito.any())).thenReturn(articles);
        List<Article> articleList = articleService.createArticles(articles);
        Assert.assertEquals("leg", articleList.get(0).getName());
    }

    /**
     * Method to test {@link ArticleServiceImplementation#getArticles()} to retrieve all articles
     * @throws Exception
     */
    @Test
    public void testGetArticles() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setName("leg");
        article.setArt_id(1);
        article.setStock(40);
        articles.add(article);
        Mockito.when(articleRepository.findAll()).thenReturn(articles);
        List<Article> articleList = articleService.getArticles();
        Assert.assertEquals("leg", articleList.get(0).getName());
    }
}
