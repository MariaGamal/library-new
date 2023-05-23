package com.vodafone.demo.service;

import com.vodafone.demo.model.Author;
import com.vodafone.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.repository = authorRepository;
    }
    @Override
    public Author getAuthorById(Integer id) {
        Optional<Author> author = this.repository.findById(id);
        return author.orElse(null);
    }

    @Override
    public Author addAuthor(Author author) {
        return this.repository.save(author);
    }
}
