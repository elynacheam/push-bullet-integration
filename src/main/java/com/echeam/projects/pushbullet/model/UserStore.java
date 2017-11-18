package com.echeam.projects.pushbullet.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserStore {

    private static final Logger logger = LoggerFactory.getLogger(UserStore.class);

    //private List<User> users = new ArrayList<>();

    // Thread safe list of users
    private List<User> users = Collections.synchronizedList(new ArrayList<>());

    /**
     * Get the list of all users
     * @return
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Add a user to the user store.
     * @param user - The user to add
     * @return     - The user present in the store representing the input user.
     */
    public synchronized User addUser(User user) {
        // Only allow a given user once
        for(User u : users) {
            if(u.getUsername().equals(user.getUsername())) {
                logger.info("Trying to add existing user");
                return u;
            }
        }
        users.add(user);
        return user;
    }
}
