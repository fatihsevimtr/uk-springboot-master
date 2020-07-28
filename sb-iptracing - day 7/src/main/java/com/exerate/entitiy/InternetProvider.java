package com.exerate.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
public class InternetProvider {

    private String name;
    private String domain;
    private String type;
    private String email;
    private String country;
    private String address;
    private String phone;
}
