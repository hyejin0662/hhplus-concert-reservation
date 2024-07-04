package com.concert_reservation.common.model;



import com.concert_reservation.common.type.GlobalResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class WebResponseData<T> {
	private GlobalResponseCode code;
	private String type;
	private String description;
	private T data;

	public static <T> WebResponseData<T> ok(T data) {
		return new WebResponseData<T>(GlobalResponseCode.SUCCESS_CODE,"concert", GlobalResponseCode.SUCCESS_CODE.getDescription(), data);
	}

	public static <T> WebResponseData<T> error(GlobalResponseCode globalResponseCode) {
		return new WebResponseData<T>(globalResponseCode,"concert", globalResponseCode.getDescription(), null);
	}
}