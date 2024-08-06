package com.concert_reservation.api.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.application.user.UserCommand;
import com.concert_reservation.api.application.user.UserInfo;
import com.concert_reservation.api.domain.concert.BookingRepository;
import com.concert_reservation.api.domain.point.PointRepository;
import com.concert_reservation.api.domain.user.model.User;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final BookingRepository bookingRepository;
	private final PointRepository pointRepository;


	@Transactional
	public UserInfo createUser(UserCommand userCommand) {

		User user = userCommand.toEntity();
		userRepository.save(user);
		return UserInfo.from(user);
	}


	public UserInfo getUser(String userId) {
		User user = userRepository.getUser(userId)
			.orElseThrow(() -> new CustomException(GlobalResponseCode.USER_NOT_FOUND));
		return UserInfo.from(user);
	}


	@Transactional
	public UserInfo updateUser(String userId, UserCommand userCommand) {
		User user = userRepository.getUser(userId)
			.orElseThrow(() -> new CustomException(GlobalResponseCode.USER_NOT_FOUND));
		user.update(userCommand);
		userRepository.save(user);
		return UserInfo.from(user);
	}


	@Transactional
	public void deleteUser(String userId) {
		bookingRepository.deleteByUserId(userId);
		pointRepository.deleteByUserId(userId);
		userRepository.deleteById(userId);
	}
}
