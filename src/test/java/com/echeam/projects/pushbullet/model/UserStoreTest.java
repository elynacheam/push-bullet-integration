package com.echeam.projects.pushbullet.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UserStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(UserStoreTest.class);

    private UserStore userStore;
    @Before
    public void setup() {
        userStore = new UserStore();
    }

    @Test
    public void testOnlyAllowsRegisterOnce() throws Exception {
        User user1 = new User("johndoe", "XXX");
        User user2 = new User("johndoe", "YYY");
        userStore.addUser(user1);
        userStore.addUser(user2);
        assertEquals(1, userStore.getAllUsers().size());
    }

    @Test
    public void testConcurrency() throws ExecutionException, InterruptedException {

        int numUsers = 3000;
        ExecutorService executorService = Executors.newFixedThreadPool(numUsers);
        List<Future> futures = new ArrayList<>();

        for(int i = 0; i < numUsers; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    userStore.addUser(new User("user" + System.nanoTime() + Math.random() * numUsers, "XXX"));
                }
            };

            futures.add(executorService.submit(task));
        }

        // wait for all tasks to complete
        for (Future future : futures) {
            future.get();
        }
        logger.info("User store now contains num users=" + userStore.getAllUsers().size());
        assertEquals("Some users failed to register - you have a concurrency issue!", numUsers, userStore.getAllUsers().size());
    }
}
