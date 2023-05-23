package com.vodafone.demo.service;

import com.vodafone.demo.controller.ArticlesController;
import com.vodafone.demo.controller.AuthorController;
import com.vodafone.demo.model.Article;
import com.vodafone.demo.model.Links;
import com.vodafone.demo.repository.ArticleRepository;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repository;

    public ArticleServiceImpl(ArticleRepository authorRepository) {
        this.repository = authorRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return this.repository.findAll();
    }

    @Override
    public Article getArticleById(Integer id) {
        Optional<Article> article = this.repository.findById(id);
        return article.orElse(null);
    }

    @Override
    public List<Article> getArticlesByAuthorName(String authorName) {
        return this.repository.findByName(authorName);
    }

    @Override
    public Article addArticle(Article article) {
        Article updatedArticle = addLinks(article);
        return this.repository.save(updatedArticle);
    }

    @Override
    public void deleteArticle(Integer id) {
        this.repository.deleteById(id);
    }

    @Override
    public Article updateArticle(Integer id, Article article) {
        deleteArticle(id);
        article.setId(id);
        return addArticle(article);
    }

    private Article addLinks(Article article){
        List<Links> links = new ArrayList<>();
        Links self = new Links();

        Link selfLink = linkTo(methodOn(ArticlesController.class)
                .getArticle(article.getId())).withRel("self");

        self.setRel("self");
        self.setHref(selfLink.getHref());

        Links authorLink = new Links();
        Link authLink = linkTo(methodOn(AuthorController.class)
                .getAuthorById(article.getAuthorId())).withRel("author");
        authorLink.setRel("author");
        authorLink.setHref(authLink.getHref());

        links.add(self);
        links.add(authorLink);
        article.setLinks(links);
        return article;
    }
}
