package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.business.model.dto.command.UserCommand;
import com.concert_reservation.api.business.model.dto.info.UserInfo;
import com.concert_reservation.api.business.model.entity.User;
import com.concert_reservation.api.business.repo.UserRepository;
import com.concert_reservation.api.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long getUserBalance(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getBalance();
    }

    @Override
    public UserResponse chargeUserBalance(String userId, Long amount) {
        // User user = userRepository.findById(userId)
        //     .orElseThrow(() -> new IllegalArgumentException("User not found"));
        // user.setBalance(user.getBalance() + amount);
        // userRepository.save(user);
        // return new UserResponse(user.getUserId(), user.getName(), user.getBalance());
        return null;
    }


    @Override
    public UserInfo createUser(UserCommand userCommand) {
        User user = User.builder()
            .userId(userCommand.getUserId())
            .name(userCommand.getName())
            .email(userCommand.getEmail())
            .phoneNumber(userCommand.getPhoneNumber())
            .balance(userCommand.getBalance())
            .build();
        userRepository.save(user);
        return UserInfo.from(user);
    }

    @Override
    public UserInfo getUser(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return UserInfo.from(user);
    }

    @Override
    public UserInfo updateUser(String userId, UserCommand userCommand) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userCommand.getName());
        user.setEmail(userCommand.getEmail());
        user.setPhoneNumber(userCommand.getPhoneNumber());
        user.setBalance(userCommand.getBalance());
        userRepository.save(user);
        return UserInfo.from(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
