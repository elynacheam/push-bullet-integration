package com.echeam.projects.pushbullet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="There was an authentication problem with Pushbullet")
public class PushBulletAuthException extends PushBulletException {
    public PushBulletAuthException() {
        super();
    }
}
