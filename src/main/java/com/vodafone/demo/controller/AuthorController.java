package com.vodafone.demo.controller;

import com.vodafone.demo.model.Article;
import com.vodafone.demo.model.Author;
import com.vodafone.demo.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(name = "id", required = false) Integer id){
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping(value = "/authors", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        author = authorService.addAuthor(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }
}
