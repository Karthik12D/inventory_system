package com.test.inventory.repository;

import com.test.inventory.entities.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;

/**
 *Repository class to handle all database operations for article
 */
@Transactional(rollbackFor = SQLDataException.class)
@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Article a set a.stock = a.stock - :amountOf where a.art_id =:art_id")
    void updateArticleStock(@Param("art_id") int art_id, @Param("amountOf") int amountOf);

}
