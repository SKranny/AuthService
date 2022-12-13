package AuthService.controllers;

import AuthService.util.captcha.CaptchaUtil;
import AuthService.dto.auth.CaptchaResponse;
import AuthService.dto.auth.LoginRequest;
import AuthService.dto.auth.RegisterRequest;
import AuthService.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.createCustomer(request);
    }

    @PostMapping("/password/recovery")
    public void createRecoveryPassRequest(@Valid @Email @RequestParam(name = "email") String email) {
        authService.createRecoveryPassRequest(email);
    }

    @PostMapping("/password/recovery/{recoveryLink}")
    public void recoveryPassword(@PathVariable("recoveryLink") String recoveryLink,
                                 @RequestParam(name = "pass") String password) {
        authService.resetPass(recoveryLink, password);
    }

    @GetMapping(value = "/captcha")
    public CaptchaResponse getCaptcha() {
        return CaptchaUtil.generateCaptcha();
    }
}
