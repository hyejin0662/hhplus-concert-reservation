package com.concert_reservation.api.domain.user.model;

import com.concert_reservation.api.application.user.UserCommand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userId;

    @Column(nullable = false)
    private String name;

    @Email
    private String email;

    @Column(nullable = false)
    private String phoneNumber;


	public User update(UserCommand userCommand) {

		this.setName(userCommand.getName());
		this.setEmail(userCommand.getEmail());
		this.setPhoneNumber(userCommand.getPhoneNumber());

		return this;
	}
}