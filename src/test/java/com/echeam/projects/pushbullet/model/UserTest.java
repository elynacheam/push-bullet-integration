package com.echeam.projects.pushbullet.model;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void testFromJson() throws Exception {

        String json = "{\n" +
                "\"username\":\"johndoe\",\n" +
                "\"accessToken\":\"AAA\"\n" +
                "}";

        User user = User.fromJson(json);

        assertEquals("johndoe", user.getUsername());
        assertEquals("AAA", user.getAccessToken());
    }

    @Test
    public void testToJson() throws Exception {
        User user = new User("johndoe", "BBB");
        String json = user.toJson();
        logger.info(json);
        User copied = User.fromJson(json);
        assertEquals("johndoe", copied.getUsername());
        assertEquals("BBB", copied.getAccessToken());
    }
}
