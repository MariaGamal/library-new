package com.vodafone.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Links {

    private Integer linksId;
    private String rel;
    private String href;

//    @ManyToOne
//    private Article article;

}