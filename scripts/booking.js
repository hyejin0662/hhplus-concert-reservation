import http from 'k6/http';
import { check } from 'k6';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

export let options = {
    scenarios: {
        booking_scenario: {
            vus: 100,
            exec: 'getBooking',
            executor: 'per-vu-iterations',
            iterations: 10,
        },
    },
};

export function getBooking() {
    let userId = randomIntBetween(1, 500);

    let res = http.get(`http://192.168.35.123:8055/bookings/${userId}`, {
    // let res = http.get(`http://localhost:8055/bookings/${userId}`, {
        tags: { name: 'getBooking' },
    });


    check(res, { 'is status 200': (r) => r.status === 200 });
}
