package com.example.trelloprojects.user.service;


import com.example.trelloprojects.user.dto.AddUserRequest;
import com.example.trelloprojects.user.dto.LoginRequest;
import com.example.trelloprojects.user.dto.UpdateEmailRequest;
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

    //To Do encode password
    @Transactional
    public User signUp(AddUserRequest request) {
        String password = passwordEncoder.encode(request.getPassword());
        return userRepository.save(new User(request, password));
    }

    @Transactional
    public void updateEmail(UpdateEmailRequest request, UserDetailsImpl userDetails) {
        User user = userRepository.findById(userDetails.getUser().getId()).orElse(null);
        user.updateEmail(request);
    }

    public void logIn(HttpServletResponse httpResponse, LoginRequest request) throws Exception {

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
    }


}
