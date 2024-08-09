package com.nanou.yaraBank.request;

public record SendRequest(String accountIdSource, String accountIdDestination, double amount){
}
