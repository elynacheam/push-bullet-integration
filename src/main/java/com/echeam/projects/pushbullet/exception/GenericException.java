package com.echeam.projects.pushbullet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="There was an internal error")
public class GenericException extends RuntimeException {
    public GenericException() {
        super();
    }
}
