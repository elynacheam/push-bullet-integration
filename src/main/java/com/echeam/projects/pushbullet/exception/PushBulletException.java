package com.echeam.projects.pushbullet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Pushbullet error")
public class PushBulletException extends RuntimeException {
    public PushBulletException() {
        super();
    }
}
