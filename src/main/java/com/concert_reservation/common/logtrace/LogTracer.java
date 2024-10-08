package com.concert_reservation.common.logtrace;

public interface LogTracer {

	TraceStatus begin(String message);
	void end(Object result, TraceStatus status);
	void exception(Object result, TraceStatus status, Exception e);
}