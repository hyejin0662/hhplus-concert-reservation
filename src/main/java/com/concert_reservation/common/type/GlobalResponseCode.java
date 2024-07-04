package com.concert_reservation.common.type;


import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Getter
public enum GlobalResponseCode {
  SUCCESS_CODE("성공적으로 작업을 수행 했습니다.", 1000, HttpStatus.OK),
  SERVER_INTERVAL_ERROR("서버 내부적 오류입니다.",1002,HttpStatus.BAD_REQUEST)

  ;

  private final String description;
  private final int CustomCode;
  private final HttpStatus httpStatus;
}