package b612.bicyclecommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import b612.bicyclecommunity.domain.course.Course;

import java.util.Optional;
import java.util.List;
import b612.bicyclecommunity.domain.user.User;


public interface CourseRepository extends JpaRepository<Course, Integer>{
    Optional<Course> findByName(String name);
    List<Course> findByCreatedUserAndOriginal(User createdUser, Boolean original);
}