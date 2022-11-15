package AuthService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Tag(name="Контроллер получения токена", description="Методы связанные с получением токена авторизации для пользователя")
public class JwtController {

    @GetMapping
    @Operation(summary = "Получение", description = "Какое то описание")
    public String get() {
        return "Get OK";
    }

    @PostMapping
    @Operation(summary = "Создание", description = "Какое то описание")
    public String post() {
        return "Post OK";
    }

    @PutMapping
    @Operation(summary = "Изменение", description = "Какое то описание")
    public String put() {
        return "Put OK";
    }

    @DeleteMapping
    @Operation(summary = "Удаление", description = "Какое то описание")
    public String delete() {
        return "Delete OK";
    }
}
