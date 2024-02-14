package com.ayiko.backend.services;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Component
public class PushNotificationService {

    public static final Logger log = LoggerFactory.getLogger(PushNotificationService.class);

    private static final String FIREBASE_SERVER_KEY = "AAAAHDQ5UcI:APA91bG983yMb4do9xEZlUyYbrC6Sc2MOl651eehfRuxRuT3yzl35Owdfz6kCukySoeLyO-nRz5K3tyztAwXVeGkoQ5I22IAg3zXpBOmHU8SKftu1aCwH9jdJOPw8voJuONVgGg4lWUM";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    private static CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
        return CompletableFuture.completedFuture(firebaseResponse);
    }

    public static ResponseEntity<String> sendNotification(String deviceToken, String bodyString) {
        JSONObject body = new JSONObject();
        body.put("to", deviceToken);
        Map<String, Object> map = new HashMap<>();
        map.put("body", bodyString);
        body.put("notification", map);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = send(request);
        CompletableFuture.allOf(pushNotification).join();
        try {
            String firebaseResponse = pushNotification.get();
            System.out.println(firebaseResponse);
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }
}
