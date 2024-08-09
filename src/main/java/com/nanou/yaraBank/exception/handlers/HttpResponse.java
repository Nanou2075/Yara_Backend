package com.nanou.yaraBank.exception.handlers;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponse {

    private int status;
    private String message;

}
