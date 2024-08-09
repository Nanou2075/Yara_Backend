
package com.nanou.yaraBank.operation;

import com.nanou.yaraBank.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequiredArgsConstructor
@RestController
public class OperationController implements OperationResource {
    private final OperationService operationService;
    @Override
    public Response createOperationDeposit(OperationRequest operation) {
       return operationService.deposit(operation.accountNumber(), operation.amount());
    }
    @Override
    public Response createOperationWithdrawal(OperationRequest operation) {
        return operationService.withdrawal(operation.accountNumber(), operation.amount());
    }
    @Override
    public Response transfert(OperationRequest operation) {
        return operationService.transfert(operation.sender(), operation.receiver(), operation.amount());
    }

    @Override
    public Response activationOperationWithdrawal(String idOperation) {
        return operationService.activatedOperation(idOperation);
    }
    @Override
    public Response operations() {
        return operationService.liste();
    }

    @Override
    public Response myOperations() {
        return operationService.listeOperation();
    }

}
