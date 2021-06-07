package com.test.inventory.domain;

import com.test.inventory.entities.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDTO {

    private List<Article> inventory;
}
