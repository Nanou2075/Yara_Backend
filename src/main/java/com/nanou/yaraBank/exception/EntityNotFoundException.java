package com.nanou.yaraBank.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {
    private int status;

    public EntityNotFoundException(int status, String message) {
        super(message);
        this.status = status;
    }

    public EntityNotFoundException(String message) {
        super(message);

    }


}
