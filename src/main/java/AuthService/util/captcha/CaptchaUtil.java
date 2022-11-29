package AuthService.util.captcha;

import AuthService.dto.auth.CaptchaResponse;
import com.github.cage.GCage;

public class CaptchaUtil {
    public static CaptchaResponse generateCaptcha() {
        GCage gCage = new GCage();
        String token = gCage.getTokenGenerator().next();

        return CaptchaResponse.builder()
                .token(token)
                .captcha(gCage.draw(token))
                .build();
    }
}
