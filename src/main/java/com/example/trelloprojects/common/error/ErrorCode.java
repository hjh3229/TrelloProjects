package com.example.trelloprojects.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // workspace
    WORKSPACE_NOT_FOUND(HttpStatus.BAD_REQUEST, "W001", "존재하지 않는 워크스페이스입니다."),
    DELETED_WORKSPACE(HttpStatus.BAD_REQUEST, "w002", "삭제된 워크스페이스에 접근할 수 없습니다."),
    ALREADY_ACTIVATED_WORKSPACE(HttpStatus.BAD_REQUEST, "w003", "이미 활성화된 워크스페이스입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}