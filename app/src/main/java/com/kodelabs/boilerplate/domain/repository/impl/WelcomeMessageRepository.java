package com.kodelabs.boilerplate.domain.repository.impl;

import com.kodelabs.boilerplate.domain.repository.MessageRepository;

/**
 * Created by deceax on 3/24/2016.
 */
public class WelcomeMessageRepository implements MessageRepository {
    @Override
    public String getWelcomeMessage() {
        String msg = "Welcome!";

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return msg;
    }
}
