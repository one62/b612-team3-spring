package b612.bicyclecommunity.repository;

import b612.bicyclecommunity.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
