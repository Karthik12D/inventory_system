package com.test.inventory.controller;

import com.test.inventory.domain.ArticleDTO;
import com.test.inventory.entities.Article;
import com.test.inventory.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

    @MockBean
    private ArticleService articleService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Method to test {@link ArticleController#createArticles(ArticleDTO)} to create articles
     * @throws Exception
     */
    @Test
    public void testCreateArticles() throws Exception {

        String articleJson = "{\n" +
                "  \"inventory\": [\n" +
                "    {\n" +
                "      \"art_id\": \"1\",\n" +
                "      \"name\": \"leg\",\n" +
                "      \"stock\": \"12\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"art_id\": \"2\",\n" +
                "      \"name\": \"screw\",\n" +
                "      \"stock\": \"17\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"art_id\": \"3\",\n" +
                "      \"name\": \"seat\",\n" +
                "      \"stock\": \"2\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"art_id\": \"4\",\n" +
                "      \"name\": \"table top\",\n" +
                "      \"stock\": \"1\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";


        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setName("leg");
        article.setArt_id(1);
        article.setStock(40);
        articles.add(article);
        Mockito.when(articleService.createArticles(Mockito.any())).thenReturn(articles);
        mockMvc.perform(MockMvcRequestBuilders.post("/inventory/createArticles").content(articleJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$[*].name").isNotEmpty());
    }

    /**
     * Method to test {@link ArticleController#createArticles(ArticleDTO)} to create improper articles
     * @throws Exception
     */
    @Test
    public void testCreateArticles_400BadRequest() throws Exception {

        String articleJson = "{\n" +
                "  \"inventory\": [\n" +
                "    {\n" +
                "      \"art_id\": \"1\",\n" +
                "      \"name\": \"leg\",\n" +
                "      \"stock\": \"12\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"art_id\": \"2\",\n" +
                "      \"name\": \"screw\",\n" +
                "      \"stock\": \"fsaf\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"art_id\": \"3\",\n" +
                "      \"name\": \"seat\",\n" +
                "      \"stock\": \"2\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"art_id\": \"4\",\n" +
                "      \"name\": \"table top\",\n" +
                "      \"stock\": \"1\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";


        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setName("leg");
        article.setArt_id(1);
        article.setStock(40);
        articles.add(article);
        Mockito.when(articleService.createArticles(Mockito.any())).thenReturn(articles);
        mockMvc.perform(MockMvcRequestBuilders.post("/inventory/createArticles").content(articleJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.jsonPath("$[*].name").isNotEmpty());
    }
}
