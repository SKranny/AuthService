package AuthService.service.recovery;

import AuthService.model.RecoveryPassRequest;
import AuthService.repository.RecoveryPassRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecoveryService {
    private final RecoveryPassRequestRepository recoveryPassRequestRepository;

    public RecoveryPassRequest createRecoveryPassRequest(String email) {
        return recoveryPassRequestRepository.save(buildRecoveryPassRequest(email));
    }

    private RecoveryPassRequest buildRecoveryPassRequest(String email) {
        return RecoveryPassRequest.builder()
                    .uuid(UUID.randomUUID().toString())
                    .email(email)
                .build();
    }

    public RecoveryPassRequest getAndDeleteRecoveryPassRequest(String uuid){
        RecoveryPassRequest passRequest = recoveryPassRequestRepository.findById(uuid)
                .orElseThrow(RuntimeException::new);
        recoveryPassRequestRepository.deleteById(uuid);
        return passRequest;
    }
}
