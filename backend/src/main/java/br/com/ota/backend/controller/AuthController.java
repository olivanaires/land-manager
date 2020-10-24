package br.com.ota.backend.controller;

import br.com.ota.backend.configuration.JwtTokenProvider;
import br.com.ota.backend.configuration.util.MessagePropertie;
import br.com.ota.backend.model.User;
import br.com.ota.backend.model.vo.LoginRequest;
import br.com.ota.backend.model.vo.LoginResponse;
import br.com.ota.backend.model.vo.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final MessagePropertie messageSource;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();
            String token = tokenProvider.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(user, token));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(messageSource.getMessage("invalid.user")));
        }
    }

}
