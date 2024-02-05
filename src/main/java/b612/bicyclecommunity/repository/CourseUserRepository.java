package b612.bicyclecommunity.repository;

import b612.bicyclecommunity.domain.courseUser.CourseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseUserRepository extends JpaRepository<CourseUser, Integer> {
}
