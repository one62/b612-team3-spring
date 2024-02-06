package b612.bicyclecommunity.service;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import b612.bicyclecommunity.domain.course.Course;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.repository.CourseRepository;
import b612.bicyclecommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;
	private final UserRepository userRepository;

	@Transactional
	public void saveCourse(String createdUserID, Integer meter, List<Pair<Double, Double>> courseArray) throws IOException {
		User createdUser = userRepository.findById(createdUserID).orElseThrow();
		Course course = Course.createCourse(createdUser, meter);
		courseRepository.saveAndFlush(course);

		String filename = "/b612bicycle/courses/" + course.getId().toString();
		CSVWriter writer = new CSVWriter(new FileWriter(filename));
		for (Pair<Double, Double> pair : courseArray) {
			String[] data = {pair.getFirst().toString(), pair.getSecond().toString()};
			writer.writeNext(data);
		}
		writer.close();
		course.setArrayUrl(filename);
		courseRepository.save(course);
	}

	public List<Pair<Double, Double>> loadCourseArray(Integer courseId) throws FileNotFoundException, IOException, CsvValidationException {
		Course course = courseRepository.findById(courseId).orElseThrow();

		String filename = course.getArrayUrl();
		CSVReader reader = new CSVReader(new FileReader(filename));
		List<Pair<Double, Double>> courseArray = new ArrayList<>(); 
		
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			courseArray.add(Pair.of(Double.parseDouble(nextLine[0]), Double.parseDouble(nextLine[1])));
		}
		reader.close();
		return courseArray;
	}
}
