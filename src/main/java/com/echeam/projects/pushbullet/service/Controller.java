package com.echeam.projects.pushbullet.service;

import com.echeam.projects.pushbullet.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.echeam.projects.pushbullet.exception.GenericException;
import com.echeam.projects.pushbullet.exception.PushBulletAuthException;
import com.echeam.projects.pushbullet.exception.PushBulletException;
import com.echeam.projects.pushbullet.exception.UserNotRegisteredException;

@RestController
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private UserStore userStore;

    @Autowired
    private PushBulletHandler pushBulletHandler;

    @RequestMapping("/")
    public String index() {
        return "Push bullet integration v.0.1.0";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userStore.getAllUsers();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return userStore.addUser(user);
    }

    @RequestMapping(value = "/pushes/{username}", method = RequestMethod.POST)
    public User sendNotification(@PathVariable final String username, @RequestBody PushNotification pushNotification) throws GenericException, UserNotRegisteredException, PushBulletException {
        User user = userStore.searchByUsername(username);
        if(user == null) {
            logger.error("Trying to sen a notification for unknown user {}", username);
            throw new UserNotRegisteredException();
        }

        RestResult res = pushBulletHandler.sendCreatePush(user, pushNotification);
        switch(res) {
            case OK:
                user.acknowledgeNotification();
                return user;
            case PUSH_BULLET_ERROR:
                throw new PushBulletException();
            case PUSH_BULLET_AUTH_ERROR:
                throw new PushBulletAuthException();
            case ERROR:
                throw new GenericException();
        }

        return null;
    }
}