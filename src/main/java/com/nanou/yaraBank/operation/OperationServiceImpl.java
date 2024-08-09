package com.nanou.yaraBank.operation;

import com.nanou.yaraBank.account.AccountDomain;
import com.nanou.yaraBank.account.AccountRepository;
import com.nanou.yaraBank.enums.AccountType;
import com.nanou.yaraBank.enums.OperationType;
import com.nanou.yaraBank.exception.EntityNotFoundException;
import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.method.MethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.nanou.yaraBank.exception.Response.Message.OPERATIONSUCCESS;
import static com.nanou.yaraBank.exception.Response.Message.OPERATIONWAITING;
import static com.nanou.yaraBank.exception.Response.Security.*;
@Service
@Transactional
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;
    private final MethodService methodService;
    @Override
    public Response withdrawal(String accountNumber, double amount) {
        AccountDomain byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        OperationDomain operationDomain = new OperationDomain();
        if (byAccountNumber.getAccountType().equals(AccountType.COURANT)) {
            if (byAccountNumber.getBalance()+ byAccountNumber.getOverDraft() < operationDomain.getAmount()){
                throw new EntityNotFoundException(NO,CHEICK_ACCOUNT);
            }
            else {
                operationDomain.setOperationDate(LocalDate.now());
                operationDomain.setType(OperationType.DEBIT);
                operationDomain.setAmount(amount);
                operationDomain.setAccountNumber(accountNumber);
                operationDomain.setValid(false);
                operationDomain.setCustomerDomain(methodService.getCurrentCustomer());
            }


        }
        if (byAccountNumber.getAccountType().equals(AccountType.EPARGNE)) {
            if (byAccountNumber.getBalance() < operationDomain.getAmount()){
                throw new EntityNotFoundException(NO,CHEICK_ACCOUNT);
            }
            operationDomain.setOperationDate(LocalDate.now());
            operationDomain.setType(OperationType.DEBIT);
            operationDomain.setAmount(amount);
            operationDomain.setAccountNumber(accountNumber);
            operationDomain.setCustomerDomain(methodService.getCurrentCustomer());
            operationDomain.setValid(false);


        }
        operationRepository.save(operationDomain);
        return new Response(OK, OPERATIONWAITING);
    }
    @Override
    public Response deposit(String accountNumber, double amount) {
        AccountDomain byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        OperationDomain operationDomain = new OperationDomain();
        operationDomain.setOperationDate(LocalDate.now());
        operationDomain.setType(OperationType.CREDIT);
        operationDomain.setValid(false);
        operationDomain.setAmount(amount);
        operationDomain.setAccountNumber(accountNumber);
        operationDomain.setCustomerDomain(methodService.getCurrentCustomer());
        operationRepository.save(operationDomain);
        return new Response(OK, OPERATIONWAITING);
    }

    @Override
    public Response transfert(String sender,String receiver,double amount) {
        AccountDomain senderAccountNumber = accountRepository.findByAccountNumber(sender);
        AccountDomain receiverAccountNumber = accountRepository.findByAccountNumber(receiver);
        OperationDomain operationDomain = new OperationDomain();
        OperationDomain operationDomain1 = new OperationDomain();
        if (senderAccountNumber.getAccountType().equals(AccountType.COURANT)) {
            if (senderAccountNumber.getBalance()+ senderAccountNumber.getOverDraft() < operationDomain.getAmount()){
                throw new EntityNotFoundException(NO,CHEICK_ACCOUNT);
            }
            else {

                operationDomain.setOperationDate(LocalDate.now());
                operationDomain.setType(OperationType.DEBIT);
                operationDomain.setAmount(amount);
                operationDomain.setAccountNumber(sender);
                operationDomain.setValid(true);
                operationDomain.setCustomerDomain(senderAccountNumber.getCustomerDomain());
                operationDomain1.setOperationDate(LocalDate.now());
                operationDomain1.setType(OperationType.CREDIT);
                operationDomain1.setAmount(amount);
                operationDomain1.setAccountNumber(receiver);
                operationDomain1.setCustomerDomain(receiverAccountNumber.getCustomerDomain());
                operationDomain1.setValid(true);
            }
        }
        if (senderAccountNumber.getAccountType().equals(AccountType.EPARGNE)) {
            if (senderAccountNumber.getBalance() < operationDomain.getAmount()){
                throw new EntityNotFoundException(NO,CHEICK_ACCOUNT);
            }
            operationDomain.setOperationDate(LocalDate.now());
            operationDomain.setType(OperationType.DEBIT);
            operationDomain.setAmount(amount);
            operationDomain.setAccountNumber(sender);
            operationDomain.setValid(true);
            operationDomain.setCustomerDomain(senderAccountNumber.getCustomerDomain());

            operationDomain1.setOperationDate(LocalDate.now());
            operationDomain1.setType(OperationType.CREDIT);
            operationDomain1.setAmount(amount);
            operationDomain1.setAccountNumber(receiver);
            operationDomain1.setCustomerDomain(receiverAccountNumber.getCustomerDomain());
            operationDomain1.setValid(true);
        }
        operationRepository.save(operationDomain);
        operationRepository.save(operationDomain1);
        senderAccountNumber.setBalance(senderAccountNumber.getBalance() - amount);
        receiverAccountNumber.setBalance(receiverAccountNumber.getBalance() +amount);
        return new Response(OK, OPERATIONSUCCESS);
    }

    @Override
    public Response activatedOperation(String operationId) {
        OperationDomain operation = operationRepository.findOperationDomainById(operationId);
        AccountDomain account = accountRepository.findByAccountNumber(operation.getAccountNumber());

        if (operation.getType().equals(OperationType.DEBIT)) {
            if (account.getAccountType().equals(AccountType.EPARGNE)) {
                if (account.getBalance() >= operation.getAmount()) {
                    throw new EntityNotFoundException(NO, CHEICK_OPERATION);
                }
                else {
                    account.setBalance(account.getBalance() - operation.getAmount());
                    accountRepository.save(account);
                }
            }
            else {
                if (account.getAccountType().equals(AccountType.COURANT)){
                    if ((account.getBalance() + account.getOverDraft()) < operation.getAmount()) {
                        throw new EntityNotFoundException(NO, CHEICK_OPERATION);
                    }
                   else {
                       account.setBalance(account.getBalance()-operation.getAmount());
                       accountRepository.save(account);
                   }

                }

            }

        }

        else if (operation.getType().equals(OperationType.CREDIT)) {
           account.setBalance(account.getBalance() + operation.getAmount());
        }
        operation.setValid(true);
        accountRepository.save(account);
        operationRepository.save(operation);
        return new Response(OK, OPERATIONSUCCESS);

    }

    @Override
    public Response liste() {
        List<OperationDomain> all = operationRepository.findAll();
        if (all.isEmpty()) {
            throw new EntityNotFoundException(NO, EMPTY_OPERATION);
        }
        return new Response(OK, all);
    }

    @Override
    public Response listeOperation() {
        List<OperationDomain> all = operationRepository.findAllOperationByCustomerDomain(methodService.getCurrentCustomer());
        if (all.isEmpty()) {
            throw new EntityNotFoundException(NO, EMPTY_OPERATION);
        }
        return new Response(OK, all);
    }
}
