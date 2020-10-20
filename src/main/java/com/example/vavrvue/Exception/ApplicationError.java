package com.example.vavrvue.Exception;

import lombok.Getter;

@Getter
public class ApplicationError {

    private final String msg;

    public ApplicationError(String msg) {
        this.msg = msg;
    }
}
