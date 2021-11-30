package com.hcl.spring.jpa.h2.model;

import lombok.Data;

@Data
public class Review {
    long revId;
    String author, subject, content;


}
