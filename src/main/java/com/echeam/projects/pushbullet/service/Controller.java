package com.echeam.projects.pushbullet.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.echeam.projects.pushbullet.model.User;
import com.echeam.projects.pushbullet.model.UserStore;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UserStore userStore;

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
}