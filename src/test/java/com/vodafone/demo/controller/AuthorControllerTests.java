package com.vodafone.demo.controller;


import com.vodafone.demo.model.Author;
import com.vodafone.demo.service.AuthorService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthorController.class)
public class AuthorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;
//    @Test
//    public void testAddAuthor() throws Exception{
//        Author inputAuthor = new Author();
//        inputAuthor.setId(1);
//        inputAuthor.setName("Maria");
//
//        Mockito.when(authorService.addAuthor(inputAuthor)).thenReturn(inputAuthor);
//
//        new JSONObject(inputAuthor);
//
//        mockMvc.perform(post("/authors")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content()
//                .andExpect(status().isOk());
//    }
}
