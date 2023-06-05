package com.vodafone.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "articles")
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="article-name")
    private String name;
    @Column(name="author-name")
    private String author;
    @Column(name="author-id")
    private Integer authorId;

//    @OneToMany
    @Transient
    private List<Links> links;

    public  Article(){}

    public Article(int i, String article1, String maria, int i1) {
    }
}



