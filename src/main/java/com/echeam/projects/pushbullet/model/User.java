package com.echeam.projects.pushbullet.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Expose
    private String username;

    @Expose
    private String accessToken;

    @Expose
    int numOfNotificationsPushed;

    @Expose
    private String creationTime = new DateTime().toString();

    public User() {
        // Empty constructor for Gson
    }

    /**
     * Constructor for non Gson purposes, e.g unit test
     * @param username    The user's username
     * @param accessToken The user's PushBullet access token
     */
    public User(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

    /**
     * Construct a user instance from a Json string.
     * @param json The Json string to deserialise
     * @return A User object
     */
    public static User fromJson(String json) {
        Gson gson = new GsonBuilder().create();
        User instance;
        instance = gson.fromJson(json, User.class);
        return instance;
    }

    /**
     * Set user's username
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get instance's username
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set instance's access token
     * @param accessToken The token to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Get instance's access token
     * @return The access token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Get the timestamp at which the user was created
     * @return The iso-8601 datetime string.
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Get the number of notifications pushed for that user
     * @return The number of notifications pushed
     */
    public int getNumOfNotificationsPushed() {
        return numOfNotificationsPushed;
    }

    /**
     * Get a Json representation of the object
     * @return The Json representation of the object
     */
    public String toJson() {
        return new Gson().toJson(this, User.class);
    }

    /**
     * Acknowledge a push notification
     */
    public void acknowledgeNotification() {
        numOfNotificationsPushed++;
    }
}
