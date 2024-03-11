package b612.bicyclecommunity.repository;

import b612.bicyclecommunity.domain.courseUser.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import b612.bicyclecommunity.domain.user.User;


public interface CourseUserRepository extends JpaRepository<CourseUser, Integer> {
	List<CourseUser> findByUser(User user);
}
