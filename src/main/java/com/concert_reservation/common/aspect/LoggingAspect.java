package com.concert_reservation.common.aspect;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.concert_reservation.support.config.slack.SlackColor;
import com.concert_reservation.support.config.slack.SlackUtils;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private static final int MAX_CACHE_SIZE = 100; // 최근 100개 메시지
    private final SlackUtils slackUtils;

    private final Map<String, AtomicReference<String>> lastMessages = new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, AtomicReference<String>> eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };

    @Autowired
    public LoggingAspect(SlackUtils slackUtils) {
        this.slackUtils = slackUtils;
    }

    @Pointcut("within(com.concert_reservation.api..*)")
    public void apiMethods() {}

    @Before("apiMethods()")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String message = "Executing method: " + className + "." + methodName;

        log.info(message);

        HashMap<String, String> data = new HashMap<>();
        data.put("Class", className);
        data.put("Method", methodName);

        sendSlackMessage("Method Call", data, SlackColor.GREEN, className + "." + methodName);
    }

    @AfterThrowing(pointcut = "apiMethods()", throwing = "ex")
    public void logMethodError(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String message = "Exception in method: " + className + "." + methodName + " with exception: " + ex.getMessage();

        log.error(message);

        HashMap<String, String> data = new HashMap<>();
        data.put("Class", className);
        data.put("Method", methodName);
        data.put("Exception", ex.getMessage());

        sendSlackMessage("Method Error", data, SlackColor.RED, className + "." + methodName + ":" + ex.getMessage());
    }

    private void sendSlackMessage(String title, HashMap<String, String> data, SlackColor color, String uniqueKey) {
        synchronized (lastMessages) {
            lastMessages.putIfAbsent(uniqueKey, new AtomicReference<>(""));
            AtomicReference<String> lastMessage = lastMessages.get(uniqueKey);

            String currentMessage = title + data.toString();
            if (!currentMessage.equals(lastMessage.get())) {
                slackUtils.sendMessage(title, data, color);
                lastMessage.set(currentMessage);
            }
        }
    }
}
