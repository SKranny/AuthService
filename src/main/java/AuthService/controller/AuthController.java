package AuthService.controller;

import AuthService.dto.auth.LoginRequest;
import AuthService.dto.auth.LoginResponse;
import AuthService.dto.auth.LogoutResponse;
import AuthService.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configurers.saml2.Saml2LogoutConfigurer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.http.HttpHeaders;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    public LogoutResponse logout(HttpServletRequest request) {
        return authService.logout(request);
    }
}
