package AuthService.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Регистрация")
public class RegisterRequest {
    @Email
    @NotBlank
    @Schema(description = "E-mail")
    private String email;

    @Schema(description = "Пароль")
    @JsonProperty(value = "password1", required = true)
    private String password;

    @Schema(description = "Подтверждение пароля")
    @JsonProperty(value = "password2", required = true)
    private String confirmPassword;

    @NotBlank
    @Schema(description = "Имя")
    private String firstName;

    @NotBlank
    @Schema(description = "Фамилия")
    private String lastName;

    @Size(min = 4, max = 6)
    @Schema(description = "Код")
    private String code;
}
