package com.hcl.spring.jpa.sql.model;

import lombok.Data;

@Data
public class Review {
    long revId;
    String author, subject, content;


}
