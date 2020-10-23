package com.example.vavrvue.Exception;

import lombok.Getter;

@Getter
public class ApplicationError {

    private final ErrorStatus errorStatus;
    private final String msg;

    public ApplicationError(ErrorStatus errorStatus, String msg) {
        this.errorStatus = errorStatus;
        this.msg = msg;
    }
}
