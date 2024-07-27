package com.concert_reservation.api.domain.user;

import java.util.Optional;

import com.concert_reservation.api.domain.user.User;

public interface UserRepository {

	Optional<User> findById(String userId);

	Optional<Object> findByConcertCode(String concertCode);
	User save(User user);
	void deleteById(String userId);
}
