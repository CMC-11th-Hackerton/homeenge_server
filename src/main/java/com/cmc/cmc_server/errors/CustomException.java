package com.cmc.cmc_server.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Custom 예외를 처리할 Exception 클래스
 */
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

}
