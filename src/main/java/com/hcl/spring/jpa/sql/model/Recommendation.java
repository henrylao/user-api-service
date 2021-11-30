package com.hcl.spring.jpa.sql.model;

import lombok.Data;

@Data
public class Recommendation {

    long destId;
    long recId;
    String author, subject, content;
    float rate;
}
