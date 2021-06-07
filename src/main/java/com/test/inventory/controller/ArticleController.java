package com.test.inventory.controller;

import com.test.inventory.domain.ArticleDTO;
import com.test.inventory.entities.Article;
import com.test.inventory.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Store provided articles into inventory system
     * @param articles the articles data provided as json
     * @return response with status of articles created successfully
     */
    @PostMapping(value = "/createArticles", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> createArticles(@RequestBody ArticleDTO articles) {
                articleService.createArticles(articles.getInventory());
        return ResponseEntity.status(HttpStatus.CREATED).body(articles.getInventory());
    }

    /**
     * Provide articles as JSON
     * @return articles as json from inventory system
     */
    @GetMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getArticles() {
        List<Article> articles = articleService.getArticles();
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setInventory(articles);
        return ResponseEntity.status(HttpStatus.OK).body(articleDTO);
    }
}
