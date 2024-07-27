package com.concert_reservation.api.domain.concert;

import java.util.List;

import com.concert_reservation.api.domain.concert.Concert;

import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;

public interface ConcertRepository {
	List<Concert> findAll();

	Optional<Concert> findById(Long concertId);

	Concert save(Concert concert);

	void deleteById(Long concertId);

	List<ConscertScheduled> getSchedules();


}

class ConcertRepoistoryImpl implements ConcertRepository {

	private ConcertScheduleJpaRepoistory concertScheduleJpaRepoistory;
	private ConcertScheduleJpaRepoistory concertScheduleJpaRepoistory;
	private ConcertScheduleJpaRepoistory concertScheduleJpaRepoistory;


	@Override
	public List<ConscertScheduled> getSchedules() {
		return null;
	}
}

class ConcertService {
	// 콘서트 스케줄 조회 (concertId, startDate, endDate) {
		List<Schedule> schedules = conertRepository.findConcertSchedule(concertId);
		return schedules.map(schedule -> ScheduleInfo.from(schedule));
// }
	// 콘서트 좌석 조회(concertId, concertScheduleId) {
		List<Seat> seats = conertRepository.findConcertSeats(concertId, concertSchduleId);
		return seats.map(seat -> seatInfo.from(seat));
//	}
	// 콘서트 예약하기(concertId, concertScheduleId, List<seatId>) {
		// 유저 가져오기
		// 이용가능한 좌석가져오기 (이선좌 예외 터트리기)
		// 좌석 임시예약 상태로 만들어 주기

		// 나는 영화관 직원이다.
		// 오늘 몇시에 어떤 영화 예약원함?
		// 좌석번호는?

		// 스케줄 있니? 영화있니?
		// 이선좌 확인

		// 임시예약 처리

	reserve(시간, 영화, 좌석번호)
	{
		var 영화 =repository.(영화).orelseThrow("존재하지 않는 영화입니다.");
		var 스케줄 = repository.(스케줄).orelseThrow("존재하지 않는 스케줄이다.");
		var 좌석=repository.(좌석).orelseThrow("존재하지 않는 좌석이다.");
		var 기존예약내역 = findPendingBooking(scheduleId, seatId);

		if (기존예약내역 != null) {
			if (기존예약내역.isValid()) {
				throw new RuntimeException("이미 예약된 좌석입니다.");
			} else {
				기존예약내역.cancel();
			}
		}
		var booking = Booking.createBooking();
		repo.saveBooking(booking);
	};
}