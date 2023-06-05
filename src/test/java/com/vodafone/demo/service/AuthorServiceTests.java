package com.vodafone.demo.service;

import com.vodafone.demo.model.Author;
import com.vodafone.demo.repository.AuthorRepository;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

//@SpringBootTest
@DisplayName("Testing the Author Service Class")
public class AuthorServiceTests {
    private AuthorService authorService;
    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorService = new AuthorServiceImpl(authorRepository);
    }
    @Test
    @DisplayName("Testing add author to db")
    public void testAddAuthor(){
        //mock
        Author inputAuthor = new Author();
        inputAuthor.setId(1);
        inputAuthor.setName("Maria");
        Mockito.when(authorRepository.save(inputAuthor)).thenReturn(inputAuthor);

        Author addedAuthor = authorService.addAuthor(inputAuthor);

        assertNotNull(addedAuthor);
        assertEquals(addedAuthor.getId(), inputAuthor.getId());
        assertEquals(addedAuthor.getName(), inputAuthor.getName());
    }

    @Test
    @DisplayName("Tesing get author by id")
    public void testGetAuthor(){
        Author inputAuthor = new Author();
        inputAuthor.setId(2);
        inputAuthor.setName("Gamal");
        Mockito.when(authorRepository.findById(2)).thenReturn(Optional.of(inputAuthor));

        Author foundAuthor = authorService.getAuthorById(2);

        assertEquals(inputAuthor.getName(), foundAuthor.getName());
    }

}
