package com.nanou.yaraBank.exception.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Setter
@Getter
@Service
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int status;
    private String message;
    private Object resultat;


    public Response(int status, String message, Object resultat) {
        this.status = status;
        this.message = message;
        this.resultat = resultat;
    }

    public Response(int status, Object resultat) {
        this.status = status;
        this.resultat = resultat;
    }
    public Response(int status, String message) {
        this.status = status;
        this.message = message;

    }


    public Response() {
    }

}
