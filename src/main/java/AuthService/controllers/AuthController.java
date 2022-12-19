package AuthService.controllers;

import AuthService.util.captcha.CaptchaUtil;
import AuthService.dto.auth.CaptchaResponse;
import AuthService.dto.auth.LoginRequest;
import AuthService.dto.auth.RegisterRequest;
import AuthService.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name="Auth Service", description="Авторизация")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Вход пользователя используя email/password")
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.createCustomer(request);
    }

    @Operation(summary = "Отправка e-mail пользователю для восстановления пароля через e-mail")
    @PostMapping("/password/recovery")
    public void createRecoveryPassRequest(@Valid @Email @RequestParam(name = "email") String email) {
        authService.createRecoveryPassRequest(email);
    }

    @Operation(summary = "Отправка ссылки для сброса пароля")
    @PostMapping("/password/recovery/{recoveryLink}")
    public void recoveryPassword(@PathVariable("recoveryLink") String recoveryLink,
                                 @RequestParam(name = "pass") String password) {
        authService.resetPass(recoveryLink, password);
    }

    @Operation(summary = "Получение капчи")
    @GetMapping(value = "/captcha")
    public CaptchaResponse getCaptcha() {
        return CaptchaUtil.generateCaptcha();
    }
}
