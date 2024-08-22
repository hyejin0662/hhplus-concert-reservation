import http from 'k6/http';
import { check } from 'k6';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';
import { sleep } from 'k6';

export let options = {
    scenarios: {
        token_scenario: {
            executor: 'per-vu-iterations',
            vus: 10,  // 10명의 가상 사용자를 시뮬레이션
            iterations: 100,  // 각 사용자당 50번 반복
            maxDuration: '1m',  // 시나리오가 최대 1분 동안 실행됨
        },
    },
};

export function setup() {
}

export function createToken() {
    const url = 'http://192.168.35.123:8055/api/tokens';

    // TokenRequest 데이터 생성
    const payload = JSON.stringify({
        userId: `user-${randomIntBetween(1, 500)}`,
        waitingAt: new Date().toISOString(),
        expirationAt: new Date(Date.now() + 60 * 60 * 1000).toISOString(), // 현재 시간부터 1시간 뒤
        tokenStatus: 'WAITING',
        concertId: randomIntBetween(1, 100),
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    check(res, {
        'is status 200': (r) => r.status === 200,
        'response contains token': (r) => r.json().hasOwnProperty('tokenId'),
    });

    sleep(1);  // 1초 대기
}

export function getToken() {
    const userId = `user-${randomIntBetween(1, 500)}`;
    const url = `http://192.168.35.123:8055/api/tokens?userId=${userId}`;

    const res = http.get(url);

    check(res, {
        'is status 200': (r) => r.status === 200,
        'response contains token': (r) => r.json().hasOwnProperty('tokenId'),
    });

    sleep(1);  // 1초 대기
}

export default function () {
    // 토큰을 생성하고, 해당 토큰의 상태를 조회하는 시나리오
    createToken();
    getToken();
}
