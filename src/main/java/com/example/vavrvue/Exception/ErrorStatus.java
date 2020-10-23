package com.example.vavrvue.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorStatus {
    private final HttpStatus type;
    public static final ErrorStatus USER_NOT_FOUND = new ErrorStatus(HttpStatus.NOT_FOUND);
    public static final ErrorStatus USER_ALREADY_EXIST = new ErrorStatus(HttpStatus.BAD_REQUEST);

    public ErrorStatus(HttpStatus type) {
        this.type = type;
    }
}
