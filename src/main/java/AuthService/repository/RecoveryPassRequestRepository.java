package AuthService.repository;

import AuthService.model.RecoveryPassRequest;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecoveryPassRequestRepository extends KeyValueRepository<RecoveryPassRequest, String> {
}
