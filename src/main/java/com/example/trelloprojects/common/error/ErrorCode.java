package com.example.trelloprojects.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // user
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "U001", "존재하지 않는 사용자입니다."),
    EXISTED_EMAIL(HttpStatus.BAD_REQUEST, "U002", "중복된 Email 입니다."),
    PASSWORD_DO_NOT_MATCH(HttpStatus.BAD_REQUEST, "U003", "비밀번호가 일치하지 않습니다."),

    // workspace
    WORKSPACE_NOT_FOUND(HttpStatus.BAD_REQUEST, "W001", "존재하지 않는 워크스페이스입니다."),
    DELETED_WORKSPACE(HttpStatus.BAD_REQUEST, "W002", "삭제된 워크스페이스에 접근할 수 없습니다."),
    ALREADY_ACTIVATED_WORKSPACE(HttpStatus.BAD_REQUEST, "W003", "이미 활성화된 워크스페이스입니다."),

    // member
    USER_DOES_NOT_BELONG_TO_WORKSPACE(HttpStatus.BAD_REQUEST, "M001", "워크스페이스의 멤버가 아닌 사용자입니다."),
    INVALID_INVITE_CODE(HttpStatus.BAD_REQUEST, "M002", "초대 코드가 유효하지 않습니다."),
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