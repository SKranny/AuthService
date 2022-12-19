package AuthService.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Распознавание капчи")
public class CaptchaResponse {
    @Schema(description = "Токен")
    private String token;

    @Builder.Default
    @Schema(description = "Тип")
    private String contentType = "image/jpeg";
    @Schema(description = "Капча")
    private byte[] captcha;
}
