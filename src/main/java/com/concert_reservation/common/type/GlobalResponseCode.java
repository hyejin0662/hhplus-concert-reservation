package com.concert_reservation.common.type;


import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Getter
public enum GlobalResponseCode {
  SUCCESS_CODE("성공적으로 작업을 수행 했습니다.", 1000, HttpStatus.OK),
  SERVER_INTERVAL_ERROR("서버 내부적 오류입니다.",1002,HttpStatus.BAD_REQUEST),
  // token
  TOKEN_NOT_FOUND("토큰이 존재하지 않습니다.",1003,HttpStatus.BAD_REQUEST),
  USER_NOT_FOUND("유저를 찾을 수 없습니다.", 1004, HttpStatus.BAD_REQUEST),
  WAITING_COUNT_NOT_FOUND("대기열에서 찾을 수 없습니다.",1005,HttpStatus.BAD_REQUEST),
 ;

  private final String description;
  private final int CustomCode;
  private final HttpStatus httpStatus;
}