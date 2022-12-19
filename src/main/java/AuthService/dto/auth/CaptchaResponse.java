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
@Schema(description = "Капча")
public class CaptchaResponse {
    private String token;

    @Builder.Default
    private String contentType = "image/jpeg";

    private byte[] captcha;
}
