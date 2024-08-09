package com.nanou.yaraBank.request;

public record DebitRequest (String accountId, double amount, String description){
}
