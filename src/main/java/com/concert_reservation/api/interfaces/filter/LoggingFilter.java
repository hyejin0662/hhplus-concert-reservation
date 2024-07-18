package com.concert_reservation.api.interfaces.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// 요청 정보 로깅
		logRequestDetails(httpRequest);

		// 다음 필터 또는 서블릿 호출
		chain.doFilter(request, response);

		// 응답 정보 로깅
		logResponseDetails(httpResponse);
	}

	private void logRequestDetails(HttpServletRequest request) {
		log.info("Request Method: {}, Request URI: {}", request.getMethod(), request.getRequestURI());
		log.info("Request Headers: {}", getHeadersInfo(request));
	}

	private void logResponseDetails(HttpServletResponse response) {
		log.info("Response Status: {}", response.getStatus());
	}

	private String getHeadersInfo(HttpServletRequest request) {
		StringBuilder headers = new StringBuilder();
		request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
			headers.append(headerName).append(": ").append(request.getHeader(headerName)).append(", ");
		});
		return headers.toString();
	}
}