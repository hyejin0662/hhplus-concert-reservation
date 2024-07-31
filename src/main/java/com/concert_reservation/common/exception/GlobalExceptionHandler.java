package com.concert_reservation.common.exception;

import static org.apache.kafka.common.protocol.Errors.*;

import java.util.NoSuchElementException;

import com.concert_reservation.common.model.WebResponseData;
import com.concert_reservation.common.type.GlobalResponseCode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<WebResponseData<Object>> customExceptionHandler(CustomException e) {
		log.error(e.getGlobalResponseCode() + "에러가 발생했습니다.");
		return new ResponseEntity<>(WebResponseData.error(e.getGlobalResponseCode()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<WebResponseData<Object>> globalExceptionHandler(Exception e) {
		log.error("서버 내부적 오류가 발생했습니다. -> " + e);
		WebResponseData<Object> response = WebResponseData.error(GlobalResponseCode.SERVER_INTERVAL_ERROR);
		throw new RuntimeException(e);
		// return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}