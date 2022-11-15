package AuthService.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogoutResponse {
    @Builder.Default
    private String error = "string";

    @Builder.Default
    private long timestamp = System.currentTimeMillis();

    @Builder.Default
    private String message = "Ok";
}
