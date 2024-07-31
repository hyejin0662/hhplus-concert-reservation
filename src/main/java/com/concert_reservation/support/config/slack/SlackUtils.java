package com.concert_reservation.support.config.slack;

import com.slack.api.Slack;
import com.slack.api.webhook.WebhookPayloads;
import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SlackUtils {

    private final String url;
    private final RestTemplate restTemplate;
    private final Slack slackClient;

    public SlackUtils(String url) {
        this.url = url;
        this.restTemplate = new RestTemplate();
        this.slackClient = Slack.getInstance();
    }

    public void sendSimpleMessage(String message) {
        SlackMessage slackMessage = new SlackMessage(message);
        restTemplate.postForEntity(url, slackMessage, String.class);
    }

    public void sendMessage(String title, HashMap<String, String> data, SlackColor color) {
        log.info("슬랙 메세지 보내기");
        try {
            slackClient.send(url, WebhookPayloads.payload(p -> p
                .text(title)
                .attachments(List.of(
                    Attachment.builder()
                        .color(color.getColorCode())
                        .fields(data.keySet().stream().map(key -> generateSlackField(key, data.get(key)))
                            .collect(Collectors.toList()))
                        .build()
                ))
            ));
        } catch (IOException e) {
            log.error("슬랙메세지 전송 실패 에러 : ", e);
        }
    }

    private Field generateSlackField(String title, String value) {
        return Field.builder()
            .title(title)
            .value(value)
            .valueShortEnough(false)
            .build();
    }

    private static class SlackMessage {
        private final String text;

        public SlackMessage(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
