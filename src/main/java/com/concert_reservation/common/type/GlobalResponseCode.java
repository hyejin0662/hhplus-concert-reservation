package com.concert_reservation.common.type;


import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Getter
public enum GlobalResponseCode {

  SUCCESS_CODE("성공적으로 작업을 수행 했습니다.", 1000, HttpStatus.OK),

  SERVER_INTERVAL_ERROR("서버 내부적 오류입니다.",1002,HttpStatus.INTERNAL_SERVER_ERROR),
  // token
  TOKEN_NOT_FOUND("토큰이 존재하지 않습니다.",1003,HttpStatus.BAD_REQUEST),
  USER_NOT_FOUND("유저를 찾을 수 없습니다.", 1004, HttpStatus.BAD_REQUEST),
  WAITING_COUNT_NOT_FOUND("대기열에서 찾을 수 없습니다.",1005,HttpStatus.BAD_REQUEST),
  TOKEN_EXPIRED("토큰이 만료되었습니다.", 1008, HttpStatus.BAD_REQUEST),
  TOKEN_NOT_PROCESSING("토큰이 처리 중이 아닙니다.", 1009, HttpStatus.BAD_REQUEST),
  INVALID_TOKEN("유효하지 않은 토큰입니다.", 1010, HttpStatus.BAD_REQUEST),

  // 예약
  BOOKING_NOT_FOUND("예약을 찾을 수 없습니다.",1014,HttpStatus.NOT_FOUND ),
  BOOKING_NOT_FOUND_BY_POINT("예약에 대한 결제 내역을 찾을 수 없습니다.",1006,HttpStatus.BAD_REQUEST),
  POINT_HISTORY_NOT_FOUND_BY_BOOKING("예약에 대한 결제 내역을 찾을 수 없습니다.", 1007, HttpStatus.BAD_REQUEST),

  // 콘서트 옵션
  INVALID_CONCERT_OPTION("콘서트 옵션이 유효하지 않습니다.", 1011, HttpStatus.BAD_REQUEST),
  // 포인트
  INSUFFICIENT_POINT("잔액이 부족합니다.", 1012, HttpStatus.BAD_REQUEST),
  PAYMENT_NOT_AVAILABLE("결제를 할 수 없습니다.", 1013, HttpStatus.BAD_REQUEST),

  // 콘서트
  CONCERT_NOT_FOUND("콘서트를 찾을 수 없습니다.", 1213, HttpStatus.NOT_FOUND),
  // 좌석
  SEAT_NOT_FOUND("좌석을 찾을 수 없습니다.", 1314, HttpStatus.NOT_FOUND),
  ALREADY_RESERVED("이미 선택된 좌석입니다", 1315,HttpStatus.NOT_FOUND)

  ;




  private final String description;
  private final int customCode;
  private final HttpStatus httpStatus;
}