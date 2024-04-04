package com.fabrik.test.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Getter
@Setter
public class Payload {

    private String date;
    private Double balance;
    private Double availableBalance;
    private String currency;

}
