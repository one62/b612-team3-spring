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

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CourseUserService {

    private final CourseUserRepository courseUserRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    @Transactional
    public void save(String userId, String courseName, LocalDateTime start, LocalDateTime end, Integer rate, String comment, Double averageSpeed, Double restMinute) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));
        Course course = courseRepository.findByName(courseName).orElseThrow(() -> new RuntimeException("해당 코스 없음"));

        CourseUser courseUser = new CourseUser(
                start,
                end,
                rate,
                comment,
                averageSpeed,
                restMinute,
                user,
                course
        );

        courseUserRepository.save(courseUser);
    }
}
