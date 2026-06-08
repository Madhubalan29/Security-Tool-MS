package com.example.dto.security;

public class TransactionInfoDto {
    private String transactionGuid;
    private String transactionName;

    public TransactionInfoDto() {}

    public TransactionInfoDto(String transactionGuid, String transactionName) {
        this.transactionGuid = transactionGuid;
        this.transactionName = transactionName;
    }

    public String getTransactionGuid() {
        return transactionGuid;
    }

    public void setTransactionGuid(String transactionGuid) {
        this.transactionGuid = transactionGuid;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
}
