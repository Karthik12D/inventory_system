package com.test.inventory.repository;

import com.test.inventory.entities.Article;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
/**
 * Class to test Article Repositories
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * Test JpaRepositories methods which used in {@link ArticleRepository}
     */
    @Test
    public void testArticleRepository() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setName("leg");
        article.setArt_id(1);
        article.setStock(40);
        articles.add(article);
        List<Article> articlesEntities = (List<Article>) articleRepository.saveAll(articles);
        Assert.assertEquals(1, articlesEntities.get(0).getArt_id());
        List<Article> articlesEntities1 = (List<Article>) articleRepository.findAll();
        Assert.assertEquals(40, articlesEntities1.get(0).getStock());
    }
}
