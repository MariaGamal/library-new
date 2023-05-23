package com.vodafone.demo.service;

import com.vodafone.demo.model.Author;

public interface AuthorService {
    Author
    getAuthorById(Integer id);

    Author addAuthor(Author author);
}
