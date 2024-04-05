package com.fabrik.test.demo.model.effettuaBonificoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MoneyTransferRequest {

    private Long accountId;
    private Creditor creditor;
    private String executionDate;
    private String uri;
    private String description;
    private String amount;
    private String currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private TaxRelief taxRelief;
}
