package com.mvc.RidePedia.auth;

import com.mvc.RidePedia.config.JwtService;
import com.mvc.RidePedia.models.User;
import com.mvc.RidePedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private static Logger logger =  LoggerFactory.getLogger(AuthService.class);



    private final AuthenticationManager authenticationManager;


























    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;





    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        // Kullanıcının kimlik doğrulaması yapılıyor
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            logger.info("Authentication: " + authentication);
            logger.info("SecurityContextHolder: " + SecurityContextHolder.getContext());
            logger.info("SecurityContextHolder.getAuthentication(): " + SecurityContextHolder.getContext().getAuthentication());
            logger.info("Authentication.isAuthenticated(): " + authentication.isAuthenticated());

            // Authentication başarılı ise
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                var repUser = (User) authentication.getPrincipal(); // Authenticated edilen kullanıcı

                // JWT token oluşturuluyor
                String token = jwtService.generateToken(repUser);



                // Yanıt dönülüyor
                return AuthenticationResponse.builder().accessToken(token).build();

            } else {
                // Authentication başarısız ise
                logger.error("Authentication failed");
                throw new RuntimeException("Authentication failed. Invalid email or password.");
            }


        } catch (Exception e) {
            // Hata durumunda loglama yapın ve uygun bir yanıt döndürün
            logger.error("Authentication failed: {}", e.getMessage());
            throw new RuntimeException("Authentication failed. Invalid email or password.");
        }
    }


    public AuthenticationResponse register(RegisterRequest registerRequest)
    {
        User user= User.builder().firstName(registerRequest.getFirstName()).lastName(registerRequest.getLastName()).email(registerRequest.getEmail()).
                password(passwordEncoder.encode(registerRequest.getPassword())).role(registerRequest.getRole()).build();

        User savedUser=userRepository.save((user));
        String token= jwtService.generateToken(savedUser);
        return AuthenticationResponse.builder().accessToken(token).build();

    }




}
