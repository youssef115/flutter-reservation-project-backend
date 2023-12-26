package com.youssef.flutterProjectBackend.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ReservationApiException extends  RuntimeException{

    private final HttpStatus status;
    private final String message;


}
