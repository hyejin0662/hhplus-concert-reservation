package com.concert_reservation.api.business.service;

import com.concert_reservation.api.business.model.dto.command.PaymentCommand;
import com.concert_reservation.api.business.model.dto.info.PaymentInfo;

public interface PaymentService {
	PaymentInfo payPoint(PaymentCommand command);
}
