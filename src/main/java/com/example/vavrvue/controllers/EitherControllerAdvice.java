package com.example.vavrvue.controllers;

import com.example.vavrvue.Exception.ApplicationError;
import com.example.vavrvue.controllers.dto.ApplicationErrorDto;
import io.vavr.control.Either;
import org.modelmapper.ModelMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Left;
import static io.vavr.Patterns.$Right;
import static io.vavr.Predicates.instanceOf;

@ControllerAdvice
public class EitherControllerAdvice  implements ResponseBodyAdvice<Object> {

    private ModelMapper modelMapper;
    public EitherControllerAdvice(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Either.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        Either either = (Either) body;
        return Match(either).of(
                Case($Right($(Objects::nonNull)), v -> v),
                Case($Left($(instanceOf(ApplicationError.class))), error -> {
                    return modelMapper.map(error, ApplicationErrorDto.class);
                }),
                Case($Right($(Objects::isNull)), v -> { throw new IllegalStateException("Null in Either#rigth"); }),
                Case($(), () -> {
                    throw new IllegalStateException("Reached illegal state in body writer");
                })
        );
    }
}
