package com.concert_reservation.common.token;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class QueueTokenGenerator {
    private static final String SECRET_KEY = "secret-key";

    /**
     * 주어진 사용자 ID를 기반으로 토큰을 생성합니다.
     *
     * <p>이 메서드는 사용자 ID와 비밀 키를 결합하여 해시 함수를 적용한 후,
     * Base64 URL 인코딩된 문자열을 반환합니다. 생성된 토큰은 주어진 사용자 ID로
     * 토큰을 조회하는 요청에 대응할 수 있게 합니다.</p>
     *
     * @param userId 토큰을 생성할 사용자 ID
     * @return 생성된 토큰 값
     * @throws RuntimeException 토큰 생성 중 오류가 발생한 경우
     */
    public static String generateToken(String userId) {
        try {
            String input = userId + SECRET_KEY;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating token", e);
        }
    }
}