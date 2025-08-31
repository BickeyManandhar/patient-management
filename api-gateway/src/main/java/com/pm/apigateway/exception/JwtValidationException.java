package com.pm.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class JwtValidationException {

    /*
    Mono - Current execution is finished
    If Auth service respond with 401, instead of api-gateway responding with 500 response
    code, we are going to intercept that and send 401.
     */
    @ExceptionHandler(WebClientResponseException.Unauthorized.class)
    public Mono<Void> handlerUnauthorizedException(ServerWebExchange ex){
        ex.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return ex.getResponse().setComplete();
    }
}
