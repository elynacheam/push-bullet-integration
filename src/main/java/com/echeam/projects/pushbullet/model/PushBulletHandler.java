package com.echeam.projects.pushbullet.model;

import com.echeam.projects.pushbullet.exception.PushBulletException;
import com.echeam.projects.pushbullet.exception.PushBulletAuthException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PushBulletHandler {

    private static final Logger logger = LoggerFactory.getLogger(PushBulletHandler.class);

    @Value("${pushbullet.createpush.endpoint:/}")
    private String createpushEndpoint;

    public RestResult sendCreatePush(User user, PushNotification pushNotification) {
        return sendJsonPostRequest(createpushEndpoint, user.getAccessToken(), pushNotification.toJson());
    }

    private RestResult sendJsonPostRequest(String url, String accessToken, String jsonBody)  {

        int statusCode = 200;
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        // Add content-type request
        post.setHeader("Content-Type", "application/json");

        // Add auth header
        post.setHeader("Access-Token", accessToken);

        try {
            post.setEntity(new StringEntity(jsonBody));
        } catch(Exception e) {
            logger.error("Failed to create post {}: {}", jsonBody, e);
            return RestResult.ERROR;
        }

        try {
            statusCode = sendPostRequest(client, post);
        } catch(IOException e) {
            logger.error("Failed to send post {}", e);
            return RestResult.ERROR;
        }

        if(statusCode == 200) {
            return RestResult.OK;
        } else if(statusCode == 401) {
            return RestResult.PUSH_BULLET_AUTH_ERROR;
        } else {
            return RestResult.PUSH_BULLET_ERROR;
        }
    }

    /**
     * Send generic post request
     * @param client
     * @param post
     * @return
     * @throws IOException
     */
    private int sendPostRequest(HttpClient client, HttpPost post) throws IOException {
        HttpResponse response = client.execute(post);
        int responseCode = response.getStatusLine().getStatusCode();

        logger.info("Response Code : {}" , responseCode);
        if(responseCode != 200) {
            return responseCode;
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.info("Response body: {}", result.toString());
        return responseCode;
    }
}
