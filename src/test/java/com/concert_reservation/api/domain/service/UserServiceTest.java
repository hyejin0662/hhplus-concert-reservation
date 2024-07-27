package com.concert_reservation.api.domain.service;

import com.concert_reservation.api.domain.user.UserCommand;
import com.concert_reservation.api.domain.user.UserInfo;
import com.concert_reservation.api.domain.user.User;
import com.concert_reservation.api.domain.user.UserRepository;
import com.concert_reservation.api.domain.user.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService 테스트")
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private User user;
	private UserCommand userCommand;

	@BeforeEach
	void setUp() {
		user = User.builder()
			.userId("user123")
			.name("김삿갓")
			.email("doe@example.com")
			.phoneNumber("123-456-7890")
			.build();

		userCommand = UserCommand.builder()
			.userId("user123")
			.name("김삿갓")
			.email("doe@example.com")
			.phoneNumber("123-456-7890")
			.balance(1000L)
			.build();
	}





	@Test
	@DisplayName("사용자 생성 성공 테스트")
	void createUserSuccessTest() {
		// given
		when(userRepository.save(any(User.class))).thenReturn(user);

		// when
		UserInfo userInfo = userService.createUser(userCommand);

		// then
		assertThat(userInfo).isNotNull();
		assertThat(userInfo.getUserId()).isEqualTo(user.getUserId());
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	@DisplayName("사용자 조회 성공 테스트")
	void getUserSuccessTest() {
		// given
		when(userRepository.findById(anyString())).thenReturn(Optional.of(user));

		// when
		UserInfo userInfo = userService.getUser("user123");

		// then
		assertThat(userInfo).isNotNull();
		assertThat(userInfo.getUserId()).isEqualTo(user.getUserId());
		verify(userRepository, times(1)).findById(anyString());
	}

	@Test
	@DisplayName("사용자 조회 실패 테스트 - 사용자 없음")
	void getUserNotFoundTest() {
		// given
		when(userRepository.findById(anyString())).thenReturn(Optional.empty());

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			userService.getUser("user123");
		});

		// then
		assertThat(exception.getMessage()).isEqualTo("유저를 찾을 수 없습니다.");
		verify(userRepository, times(1)).findById(anyString());
	}

	@Test
	@DisplayName("사용자 정보 업데이트 성공 테스트")
	void updateUserSuccessTest() {
		// given
		when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);

		// when
		UserInfo userInfo = userService.updateUser("user123", userCommand);

		// then
		assertThat(userInfo).isNotNull();
		assertThat(userInfo.getUserId()).isEqualTo(user.getUserId());
		verify(userRepository, times(1)).findById(anyString());
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	@DisplayName("사용자 정보 업데이트 실패 테스트 - 사용자 없음")
	void updateUserNotFoundTest() {
		// given
		when(userRepository.findById(anyString())).thenReturn(Optional.empty());

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			userService.updateUser("user123", userCommand);
		});

		// then
		assertThat(exception.getMessage()).isEqualTo("유저를 찾을 수 없습니다.");
		verify(userRepository, times(1)).findById(anyString());
		verify(userRepository, never()).save(any(User.class));
	}


}
