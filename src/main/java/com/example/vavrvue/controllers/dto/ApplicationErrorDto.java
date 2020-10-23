package com.example.vavrvue.controllers.dto;

import com.example.vavrvue.Exception.ErrorStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationErrorDto {
    private ErrorStatus errorStatus;
    private String msg;
}
