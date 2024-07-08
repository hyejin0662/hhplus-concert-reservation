package com.concert_reservation.common.exception;

import com.concert_reservation.common.type.GlobalResponseCode;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private GlobalResponseCode globalResponseCode;
    private String description;

    public CustomException(GlobalResponseCode globalResponseCode) {
        this.globalResponseCode = globalResponseCode;
        this.description = globalResponseCode.getDescription();
    }


    public CustomException(GlobalResponseCode globalResponseCode, String message) {
        super(message);
        this.globalResponseCode = globalResponseCode;
        this.description = message != null ? message : globalResponseCode.getDescription();
    }

    public CustomException(GlobalResponseCode globalResponseCode, String message,
        Throwable cause) {
        super(message, cause);
        this.globalResponseCode = globalResponseCode;
        this.description = message != null ? message : globalResponseCode.getDescription();
    }

    public CustomException(GlobalResponseCode globalResponseCode, Throwable cause) {
        super(cause);
        this.globalResponseCode = globalResponseCode;
        this.description = globalResponseCode.getDescription();
    }

    @Override
    public String getMessage() {
        return description != null ? description : super.getMessage();
    }
}

