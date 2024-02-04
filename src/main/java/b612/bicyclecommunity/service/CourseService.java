package b612.bicyclecommunity.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVWriter;

import b612.bicyclecommunity.domain.course.Course;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;

	@Transactional
	public void saveCourse(User user, Integer meter, List<Pair<Double, Double>> courseArray) throws IOException {
		Course course = Course.createCourse(user, meter);
		courseRepository.saveAndFlush(course);

		String filename = "/b612bicycle/courses/" + course.getId().toString();
		CSVWriter writer = new CSVWriter(new FileWriter(filename));
		for (Pair<Double, Double> pair : courseArray) {
			String[] data = {pair.getFirst().toString(), pair.getSecond().toString()};
			writer.writeNext(data);
		}
		writer.close();
		course.setArrayUrl(filename);
	}
}
