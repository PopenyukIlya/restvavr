package com.example.vavrvue.controllers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationErrorDto {
    private int errorStatus;
    private String userMessage;
}
