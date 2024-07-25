// package com.concert_reservation.support;
//
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Propagation;
//
// import jakarta.transaction.Transactional;
//
// /**
//  * AOP에서 트랜잭션 분리를 위한 클래스
//  */
// @Component
// public class AopForTransaction {
//
//     @Transactional(propagation = Propagation.REQUIRES_NEW)
//     public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
//         return joinPoint.proceed();
//     }
// }