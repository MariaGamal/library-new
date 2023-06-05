package com.vodafone.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.demo.model.Article;
import com.vodafone.demo.service.ArticleService;
import com.vodafone.demo.service.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@WebMvcTest(ArticlesController.class)
public class ArticlesControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArticleService articleService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Getting All Articles Test")
    public void testGetArticles_ReturnsListOfArticles() throws Exception{
        List<Article> articles = new ArrayList<>();
        Article article = new Article(1, "article1", "Maria", 2);
        articles.add(article);
        Mockito.when(articleService.getAllArticles()).thenReturn(articles);

        mockMvc.perform(get("/v1/articles")
                )
                .andExpectAll(
                        status().isOk()
                );
    }
    @Test
    @DisplayName("Getting Article By Id Test")
    public void testGetArticleById_ReturnsArticleObject() throws Exception{
        Article article = new Article(1, "article1", "Maria", 2);
        Mockito.when(articleService.getArticleById(article.getId())).thenReturn(article);

        mockMvc.perform(get("/v1/articles/{id}", 1)
                )
                .andExpect(
                        status().isOk()
                );
    }

    @Test
    @DisplayName("Add Article Test")
    public void testAddArticle_AddsArticleToDatabase() throws Exception{
        Article article = new Article(1, "updated", "Maria", 2);
        Mockito.when(articleService.addArticle(article)).thenReturn(article);

        mockMvc.perform(post("/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(article))
                )
                .andExpect(
                        status().isCreated()
                );
    }

    @Test
    @DisplayName("Update Article Test")
    public void testUpdateArticleById_GivenUpdatedArticle_SavesArticleById() throws Exception{
        Article updatedArticle = new Article(1, "updated", "Maria", 2);
        Mockito.when(articleService.updateArticle(1, updatedArticle)).thenReturn(updatedArticle);

        mockMvc.perform(put("/v1/articles/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedArticle))
                )
                .andExpect(
                        status().isOk()
                );
    }

    @Test
    @DisplayName("Delete Article Test")
    public void testDeleteArticleById_GivenIntegerId_DeletesArticle() throws Exception{
        doNothing().when(articleService).deleteArticle(1);

        mockMvc.perform(delete("/v1/articles/{id}", 1)
                )
                .andExpect(
                        status().isNoContent()
                );
    }


}
