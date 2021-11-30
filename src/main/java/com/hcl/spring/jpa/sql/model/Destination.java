package com.hcl.spring.jpa.sql.model;

import lombok.Data;

@Data
public class Destination {
    long destId;
    String country, place;
    float longitude, latitude;
    String info;
//    Image images;
}
