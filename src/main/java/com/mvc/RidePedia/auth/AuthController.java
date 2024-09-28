package com.mvc.RidePedia.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ridepedia/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest)
    {
        try {

            AuthenticationResponse authResponse = authService.register(registerRequest);

            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {

            e.printStackTrace(); // Hata detaylarını konsola yazdırır
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    private static Logger logger =  LoggerFactory.getLogger(AuthController.class);
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationrequest)
    {


      return new ResponseEntity<>(authService.authenticate(authenticationrequest), HttpStatus.OK);


    }

}
