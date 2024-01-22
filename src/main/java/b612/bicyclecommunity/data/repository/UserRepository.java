package b612.bicyclecommunity.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import b612.bicyclecommunity.data.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);
}
