package com.example.trelloprojects.user.controller;

import com.example.trelloprojects.common.dto.MsgResponseDto;
import com.example.trelloprojects.user.dto.AddUserRequest;
import com.example.trelloprojects.user.dto.CheckPasswordRequest;
import com.example.trelloprojects.user.dto.LoginRequest;
import com.example.trelloprojects.user.dto.UpdateEmailRequest;
import com.example.trelloprojects.user.dto.UpdatePasswordRequest;
import com.example.trelloprojects.user.entity.UserDetailsImpl;
import com.example.trelloprojects.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<MsgResponseDto> signUp(@RequestBody AddUserRequest request) {
        userService.signUp(request);
        return ResponseEntity.ok(new MsgResponseDto("회원가입 성공", HttpStatus.OK.value()));
    }

    @PostMapping("/log-in")
    public ResponseEntity<MsgResponseDto> logIn(HttpServletResponse httpResponse,
            @RequestBody LoginRequest request) {
        userService.logIn(httpResponse, request);
        return ResponseEntity.ok(new MsgResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

    @PutMapping("/email")
    public ResponseEntity<MsgResponseDto> updateEmail(@RequestBody UpdateEmailRequest request,
            @AuthenticationPrincipal
            UserDetailsImpl userDetails) {
        userService.updateEmail(request, userDetails);
        return ResponseEntity.ok(new MsgResponseDto("이메일 수정 성공, 재로그인하세요.", HttpStatus.OK.value()));
    }

    @PutMapping("/password")
    public ResponseEntity<MsgResponseDto> updatePassword(@RequestBody UpdatePasswordRequest request,
            @AuthenticationPrincipal
            UserDetailsImpl userDetails) {
        userService.updatePassword(request, userDetails);
        return ResponseEntity.ok(new MsgResponseDto("비밀번호 변경 성공", HttpStatus.OK.value()));
    }

    @PutMapping("/withdraw")
    public ResponseEntity<MsgResponseDto> withdraw(@RequestBody CheckPasswordRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.withDraw(request, userDetails);
        return ResponseEntity.ok(new MsgResponseDto("휴면 계정 변경 성공", HttpStatus.OK.value()));
    }

    @PutMapping("/activate")
    public ResponseEntity<MsgResponseDto> activate(@RequestBody LoginRequest request) {
        userService.activate(request);
        return ResponseEntity.ok(
                new MsgResponseDto("휴면 계정 활성화 성공, 재로그인하세요.", HttpStatus.OK.value()));
    }

}
