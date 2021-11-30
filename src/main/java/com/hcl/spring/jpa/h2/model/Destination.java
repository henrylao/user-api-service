package com.hcl.spring.jpa.h2.model;

import lombok.Data;

@Data
public class Destination {
    long destId;
    String country, place;
    float longitude, latitude;
    String info;
//    Image images;
}
