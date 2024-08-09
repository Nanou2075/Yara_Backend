package com.nanou.yaraBank.request;

public record CreditRequest(String accountId, double amount, String description){
}
