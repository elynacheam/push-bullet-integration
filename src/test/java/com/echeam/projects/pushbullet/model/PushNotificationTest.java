package com.echeam.projects.pushbullet.model;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class PushNotificationTest {

    private static final Logger logger = LoggerFactory.getLogger(PushNotificationTest.class);

    @Test
    public void testToJson() throws Exception {
        PushNotification p = new PushNotification();
        p.setTitle("Happy Birthday");
        p.setBody("Many happy returns on your birthday");
        String json = p.toJson();
        logger.info(json);
    }
}
