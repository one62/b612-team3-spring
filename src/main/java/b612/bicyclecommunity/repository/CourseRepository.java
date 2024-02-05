package b612.bicyclecommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import b612.bicyclecommunity.domain.course.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer>{
    Optional<Course> findByName(String name);

}