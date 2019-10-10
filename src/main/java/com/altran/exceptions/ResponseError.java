package com.altran.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseError {

    private String code;
    private String message;
}
