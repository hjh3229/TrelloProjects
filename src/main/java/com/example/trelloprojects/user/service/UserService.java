package com.example.trelloprojects.user.service;


import com.example.trelloprojects.common.error.BusinessException;
import com.example.trelloprojects.common.error.ErrorCode;
import com.example.trelloprojects.user.dto.AddUserRequest;
import com.example.trelloprojects.user.dto.LoginRequest;
import com.example.trelloprojects.user.dto.UpdateEmailRequest;
import com.example.trelloprojects.user.dto.UpdatePasswordRequest;
import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.user.entity.UserDetailsImpl;
import com.example.trelloprojects.user.repository.UserRepository;
import com.example.trelloprojects.user.security.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final TokenProvider tokenProvider;

    @Transactional
    public User signUp(AddUserRequest request) {
        User checkUser = userRepository.findByEmail(request.getEmail());

        if (checkUser != null) {
            throw new BusinessException(ErrorCode.EXISTED_EMAIL);
        }

        return userRepository.save(
                new User(request, passwordEncoder.encode(request.getPassword())));
    }

    @Transactional
    public void updateEmail(UpdateEmailRequest request, UserDetailsImpl userDetails) {
        User user = findUser(userDetails);

        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new BusinessException(ErrorCode.EXISTED_EMAIL);
        }

        user.updateEmail(request);
    }

    @Transactional
    public void updatePassword(UpdatePasswordRequest request, UserDetailsImpl userDetails) {
        User user = findUser(userDetails);

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getUser().getPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_DO_NOT_MATCH);
        }

        user.updatePassword(passwordEncoder.encode(request.getNewPassword()));
    }

    public User findUser(UserDetailsImpl userDetails) {
        return userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );
    }

    public void logIn(HttpServletResponse httpResponse, LoginRequest request) {
        User checkUser = userRepository.findByEmail(request.getEmail());

        if (checkUser != null) {
            throw new BusinessException(ErrorCode.EMAIL_DO_NOT_MATCH);
        }

        if (!passwordEncoder.matches(request.getPassword(), request.getPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_DO_NOT_MATCH);
        }

        try {
            Authentication authentication = authenticationConfiguration.getAuthenticationManager()
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()
                            )
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

            httpResponse.addHeader(TokenProvider.HEADER_AUTHORIZATION,
                    tokenProvider.generateToken(user, Duration.ofHours(2)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
