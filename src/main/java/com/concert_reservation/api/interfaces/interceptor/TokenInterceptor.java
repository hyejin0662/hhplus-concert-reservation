package com.concert_reservation.api.interfaces.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.concert_reservation.api.application.dto.response.TokenValidateResponse;
import com.concert_reservation.api.application.facade.TokenFacade;
import com.concert_reservation.common.annotation.ValidatedToken;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

	private final TokenFacade tokenFacade;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			ValidatedToken validatedToken = method.getMethodAnnotation(ValidatedToken.class);

			if (validatedToken != null) {
				String token = request.getHeader("x-queue-token");

				if (StringUtils.isEmpty(token)) {
					throw new CustomException(GlobalResponseCode.INVALID_TOKEN);
				}

				TokenValidateResponse validate = tokenFacade.validate(token);

				if (!validate.isValid()) {
					throw new CustomException(GlobalResponseCode.INVALID_TOKEN);
				}

			}
		}
		return true;
	}
}