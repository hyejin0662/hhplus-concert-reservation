package com.concert_reservation.config.slack;

import lombok.Getter;

@Getter
public enum SlackColor {
    RED("#ff0000"),
    GREEN("#008000");

    private final String colorCode;

    SlackColor(String colorCode) {
        this.colorCode = colorCode;
    }
}