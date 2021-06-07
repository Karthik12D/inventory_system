package com.test.inventory.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Error {

    private String message;

    private String code;

    private Date date;
}
