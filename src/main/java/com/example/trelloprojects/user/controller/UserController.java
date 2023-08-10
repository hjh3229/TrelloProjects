package com.example.trelloprojects.user.controller;

import com.example.trelloprojects.user.dto.AddUserRequest;
import com.example.trelloprojects.user.dto.LoginRequest;
import com.example.trelloprojects.user.dto.UpdateEmailRequest;
import com.example.trelloprojects.user.dto.UpdatePasswordRequest;
import com.example.trelloprojects.user.entity.User;
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
    public ResponseEntity<User> signUp(@RequestBody AddUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(request));
    }

    @PostMapping("/log-in")
    public ResponseEntity<Void> logIn(HttpServletResponse httpResponse,
            @RequestBody LoginRequest request) {
        userService.logIn(httpResponse, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/email")
    public ResponseEntity<Void> updateEmail(@RequestBody UpdateEmailRequest request,
            @AuthenticationPrincipal
            UserDetailsImpl userDetails) {
        userService.updateEmail(request, userDetails);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordRequest request,
            @AuthenticationPrincipal
            UserDetailsImpl userDetails) {
        userService.updatePassword(request, userDetails);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/withdraw")
    public ResponseEntity<Void> withdraw() {
        return ResponseEntity.ok().build();
    }

}
