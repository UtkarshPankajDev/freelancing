package com.freelance.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse extends RuntimeException{
    private int statusCode;
    private String message;

    public ErrorResponse(String message) {
        super();
        this.message = message;
    }
}
