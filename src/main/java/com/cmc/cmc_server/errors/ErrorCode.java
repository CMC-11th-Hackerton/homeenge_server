package com.cmc.cmc_server.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom 예외를 관리하기 위한 클래스 입니다.
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글 정보를 찾을 수 없습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다."),

    USER_ALREADY_REACTION(HttpStatus.BAD_REQUEST, "이미 좋아요 혹은 싫어요를 누른 유저입니다."),

    NOT_MATCH_ANSWER(HttpStatus.BAD_REQUEST, "답안이 일치하지 않습니다."),

    FORBIDDEN_USER(HttpStatus.FORBIDDEN, "권한이 없는 요청입니다."),

    FORBIDDEN_AUTHORIZATION(HttpStatus.FORBIDDEN, "인증되지 않은 요청입니다."),

    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),

    PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");

    private final HttpStatus Status;
    private final String message;

}
