package com.altran.exceptions;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String cod;

    private final String message;

    public ValidationException(String cod, String message){
        super();
        this.cod = cod;
        this.message = message;
    }

}
