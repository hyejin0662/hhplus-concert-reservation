package com.concert_reservation.api.business.repo;

import java.util.List;
import java.util.Optional;

import com.concert_reservation.api.business.model.entity.User;

public interface UserRepository {

	Optional<User> findById(String userId);

	Optional<Object> findByConcertCode(String concertCode);
	User save(User user);
	void deleteById(String userId);
}
