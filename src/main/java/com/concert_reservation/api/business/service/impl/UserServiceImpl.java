package com.concert_reservation.api.business.service.impl;

import com.concert_reservation.api.application.dto.response.UserResponse;
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
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
        return new UserResponse(user.getUserId(), user.getName(), user.getBalance());
    }
}
