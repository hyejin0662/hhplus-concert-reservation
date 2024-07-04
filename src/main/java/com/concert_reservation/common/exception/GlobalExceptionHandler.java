package com.concert_reservation.common.exception;

import com.concert_reservation.common.model.WebResponseData;
import com.concert_reservation.common.type.GlobalResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConcertException.class)
	public WebResponseData<Object> lectureExceptionHandler(ConcertException e) {
		log.error(e.getGlobalResponseCode() + "에러가 발생했습니다.");
		return WebResponseData.error(e.getGlobalResponseCode());
	}

	@ExceptionHandler(Exception.class)
	public WebResponseData<Object> globalExceptionHandler(Exception e) {
		log.error("서버 내부적 오류가 발생했습니다. -> "+e);
		return WebResponseData.error(GlobalResponseCode.SERVER_INTERVAL_ERROR);
	}
}