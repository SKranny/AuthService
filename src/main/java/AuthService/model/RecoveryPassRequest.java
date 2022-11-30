package AuthService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@RedisHash
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecoveryPassRequest {
    @Id
    private String uuid;

    private String email;

    @TimeToLive(unit = TimeUnit.HOURS)
    @Builder.Default
    private Integer timeToLiveInHours = 24;
}
