package b612.bicyclecommunity.service;

import b612.bicyclecommunity.domain.course.Course;
import b612.bicyclecommunity.domain.courseUser.CourseUser;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.repository.CourseRepository;
import b612.bicyclecommunity.repository.CourseUserRepository;
import b612.bicyclecommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseUserService {

    private final CourseUserRepository courseUserRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public void saveCourseUser(String userId, Integer courseId, String startTime, String endTime, int elapsedTime,
    int rating, int difficulty, String review) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("해당 코스 없음"));

        CourseUser cu = CourseUser.createCourseUser(startTime, endTime, elapsedTime, rating, difficulty, review, user, course);

        courseUserRepository.save(cu);
    }

    public List<CourseUser> loadCourseUserByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));
        return courseUserRepository.findByUser(user);
    }
}
