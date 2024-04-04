package com.fabrik.test.demo.model.effettuaBonificoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MoneyTransferRequest {


/*    private Long accountId;
    private Creditor creditorName;
    private String executionDate;
    private String amount;
    private String description;
    private String currency;
}*/

    private Long accountId;
    private Creditor creditor;
    private String executionDate;
    private String uri;
    private String description;
    private Integer amount;
    private String currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private TaxRelief taxRelief;
}
