package com.concert_reservation.api.domain.user;

import com.concert_reservation.api.domain.concert.BookingRepository;
import com.concert_reservation.api.domain.point.PointRepository;
import com.concert_reservation.api.domain.queue.TokenRepository;
import com.concert_reservation.common.exception.CustomException;
import com.concert_reservation.common.type.GlobalResponseCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.concert_reservation.api.domain.user.UserCommand;
import com.concert_reservation.api.domain.user.UserInfo;
import com.concert_reservation.api.domain.user.User;
import com.concert_reservation.api.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;
    private final PointRepository pointRepository;
    private final TokenRepository tokenRepository;




    @Override
    @Transactional
    public UserInfo createUser(UserCommand userCommand) {
        User user = User.builder()
            .userId(userCommand.getUserId())
            .name(userCommand.getName())
            .email(userCommand.getEmail())
            .phoneNumber(userCommand.getPhoneNumber())
            .build();
        userRepository.save(user);
        return UserInfo.from(user);
    }

    @Override
    public UserInfo getUser(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() ->new CustomException(GlobalResponseCode.USER_NOT_FOUND));
        return UserInfo.from(user);
    }

    @Override
    @Transactional
    public UserInfo updateUser(String userId, UserCommand userCommand) {
        User user = userRepository.findById(userId)
            .orElseThrow(() ->new CustomException(GlobalResponseCode.USER_NOT_FOUND));
        user.setName(userCommand.getName());
        user.setEmail(userCommand.getEmail());
        user.setPhoneNumber(userCommand.getPhoneNumber());
        userRepository.save(user);
        return UserInfo.from(user);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        bookingRepository.deleteByUserId(userId);
        pointRepository.deleteByUserId(userId);
        tokenRepository.deleteByUserId(userId);
        userRepository.deleteById(userId);
    }
}
