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
    private String email;

    @JsonProperty(value = "password1", required = true)
    private String password;

    @JsonProperty(value = "password2", required = true)
    private String confirmPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Size(min = 4, max = 6)
    private String code;
}
