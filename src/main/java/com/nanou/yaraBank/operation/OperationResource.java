package com.nanou.yaraBank.operation;


import com.nanou.yaraBank.exception.Response.Response;
import org.springframework.web.bind.annotation.*;

public interface OperationResource {
    @PostMapping("deposit")
    Response createOperationDeposit(@RequestBody OperationRequest operation);
    @PostMapping("withdrawal")
    Response createOperationWithdrawal(@RequestBody OperationRequest operation);
    @PostMapping("transfert")
    Response transfert(@RequestBody OperationRequest operation);
    @GetMapping("activation/{idOperation}")
    Response activationOperationWithdrawal(@PathVariable String idOperation);
    @GetMapping("liste")
    Response operations();
    @GetMapping("myliste")
    Response myOperations();
}
