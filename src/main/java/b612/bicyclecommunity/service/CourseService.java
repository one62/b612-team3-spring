package b612.bicyclecommunity.service;

import java.util.List;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import b612.bicyclecommunity.domain.course.Course;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;

	public void saveCourse(User user, Integer meter, List<Pair<Double, Double>> courseArray) {
		Course course = Course.createCourse(user, meter);
		course.getId();
	}
}
