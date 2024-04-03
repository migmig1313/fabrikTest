package com.fabrik.test.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Getter
@Setter
public class Payload implements Serializable {

    public String date;
    public float balance;
    public float availableBalance;

    public String currency;

}
