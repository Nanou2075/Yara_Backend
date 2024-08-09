package com.nanou.yaraBank.operation;

public record OperationRequest(String sender,String receiver,String accountNumber,double amount) {
}
