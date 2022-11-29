package AuthService.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaResponse {
    private String token;

    @Builder.Default
    private String contentType = "image/jpeg";

    private byte[] captcha;
}
