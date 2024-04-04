package com.fabrik.test.demo.model.effettuaBonificoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Creditor {
        private String name;
        private Account account;
        private Address address;
}
