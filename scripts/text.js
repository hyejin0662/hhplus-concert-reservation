import http from 'k6/http';
import { sleep } from 'k6';
import { SharedArray } from 'k6/data';

export let options = {
    vus: 10, // 가상 사용자 수
    duration: '30s', // 테스트 실행 시간

    stages: [
        { duration: '5m', target:200 }, //ramp up
        { duration: '20m', target:200 }, // stable
        { duration: '5m', target:0 } // ramp-down to 0 user
    ],
};

const dates = new SharedArray({

});

export default function () {
    // 기본 URL
    // http.get('https://test.k6.io');http://host.docker.internal:8080/api/concerts/
    http.get('http://host.docker.internal:8055/users/1')

    // 각 엔드포인트에 대해 GET 요청
    // let responses = http.batch([
    //     ['GET', `${baseUrl}/`, null, { tags: { name: 'HomePage' } }],
    //     ['GET', `${baseUrl}/about`, null, { tags: { name: 'AboutPage' } }],
    //     ['GET', `${baseUrl}/contact`, null, { tags: { name: 'ContactPage' } }],
    // ]);

    // // 응답 확인 및 로그 기록
    // check(responses[0], {
    //     'Home page status was 200': (r) => r.status === 200,
    // });
    //
    // check(responses[1], {
    //     'About page status was 200': (r) => r.status === 200,
    // });
    //
    // check(responses[2], {
    //     'Contact page status was 200': (r) => r.status === 200,
    // });

    // 요청 간격을 위한 대기 시간
    sleep(1);
}


