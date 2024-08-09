package com.nanou.yaraBank.operation;


import com.nanou.yaraBank.exception.Response.Response;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface OperationService {

    Response withdrawal(String accountNumber, double amount);

    Response deposit(String accountNumber, double amount);

    Response transfert(String sender, String receiver, double amount);

    Response activatedOperation(String operationId);

    Response liste();

    Response listeOperation();
}
