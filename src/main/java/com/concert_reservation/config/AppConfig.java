package com.concert_reservation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.concert_reservation.config.slack.SlackUtils;

@Configuration
public class AppConfig {

    @Bean
    public SlackUtils slackUtils(@Value("${api.slack.url}") String url) {
        return new SlackUtils(url);
    }
}