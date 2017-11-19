package com.echeam.projects.pushbullet.model;

import com.google.gson.Gson;

public class PushNotification {

    private String title;
    private String body;
    private String type = "note";

    public PushNotification() {
        // Empty constructor for Gson
    }

    // Constructs a "note" notification
    public PushNotification(String body, String title) {
        this.body = body;
        this.title = title;
    }

    /**
     * Set notification title
     * @param title The title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get notification title
     * @return The notification title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set notification title
     * @param body The body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Get notification body.
     * @return The notification body
     */
    public String getBody() {
        return body;
    }

    public String getType() { return type; }

    /**
     * Get a Json representation of the object
     * @return The Json representation of the object
     */
    public String toJson() {
        return new Gson().toJson(this, PushNotification.class);
    }

    @Override
    public String toString() {
        return "Push [title=" + title + " body=" + body + "]";
    }
}
