package com.vodafone.demo.service;

import com.vodafone.demo.model.Article;
import com.vodafone.demo.repository.ArticleRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
public class ArticleServiceTests {
    @Autowired
    private ArticleService articleService;
    @MockBean
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("Testing get all articles")
    public void testGetAllArticles_ReturnsAllArticlesList(){
        List<Article> articles = new ArrayList<>();
        Article article = new Article(1, "article1", "Maria", 2);
        articles.add(article);
        Mockito.when(articleRepository.findAll()).thenReturn(articles);

        List<Article> foundArticles = articleService.getAllArticles();

        assertNotNull(foundArticles);
        assertEquals(foundArticles.size(), articles.size());
        assertEquals(foundArticles.get(0).getName(), articles.get(0).getName());
    }
    @Test
    @DisplayName("Tesing get article by id")
    public void testGetArticleById_ReturnsArticleObject(){
        Article article = new Article(1, "article1", "Maria", 2);
        Mockito.when(articleRepository.findById(1)).thenReturn(Optional.of(article));

        Article foundArticle = articleService.getArticleById(1);

        assertEquals(article.getName(), foundArticle.getName());
    }
    @Test
    @DisplayName("Tesing get article by author name")
    public void testGetArticleByAuthorName_ReturnsArticleObject(){
        List<Article> articles = new ArrayList<>();
        Article article = new Article(1, "article1", "Maria", 2);
        Article article2 = new Article(2, "article2", "Maria", 2);
        articles.add(article);
        articles.add(article2);
        Mockito.when(articleRepository.findByName("Maria")).thenReturn(articles);

        List<Article> foundArticles = articleService.getArticlesByAuthorName("Maria");

        assertNotNull(foundArticles);
        assertEquals(foundArticles.size(), articles.size());
        assertEquals(foundArticles.get(0).getName(), articles.get(0).getName());
        assertEquals(foundArticles.get(1).getId(), articles.get(1).getId());
    }

    @Test
    @DisplayName("Testing add article to db")
    public void testAddArticle_ReturnsNothing(){
        //mock
        Article article = new Article(1, "article1", "Maria", 2);
        Mockito.when(articleRepository.save(article)).thenReturn(article);

        Article addedArticle = articleService.addArticle(article);

        assertNotNull(addedArticle);
        assertEquals(addedArticle.getId(), article.getId());
        assertEquals(addedArticle.getName(), article.getName());
    }

    @Test
    @DisplayName("Element not found, throwing exception when trying to delete it")
    public void testDeleteArticle_ElementNotFound_ExceptionReturned(){
        assertThrows(NoSuchElementException.class, () -> articleService.deleteArticle(1));
    }

    @Test
    @DisplayName("Updating article")
    public void testUpdateArticle_ElementFound(){
        //mock
        Article article = new Article(1, "old", "Maria", 2);
        Article newArticle = new Article(1, "updated", "Maria", 2);
        Mockito.when(articleRepository.findById(article.getId())).thenReturn(Optional.of(article));
        Mockito.when(articleRepository.save(newArticle)).thenReturn(newArticle);

        Article updatedArticle = articleService.updateArticle(article.getId(), newArticle);

        assumeTrue(updatedArticle != null);
        assertEquals(updatedArticle.getId(), article.getId());
        assertEquals(updatedArticle.getName(), newArticle.getName());
    }
}
