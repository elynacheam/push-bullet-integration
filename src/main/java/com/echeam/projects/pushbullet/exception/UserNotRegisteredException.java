package com.echeam.projects.pushbullet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User is not registered with this service")
public class UserNotRegisteredException extends RuntimeException {

    public UserNotRegisteredException() {
        super();
    }
}
