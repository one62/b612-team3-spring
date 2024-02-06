package b612.bicyclecommunity.service;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
	public Integer saveCourse(String createdUserID, Integer meter, List<List<Double>> courseArray) throws IOException {
		User createdUser = userRepository.findById(createdUserID).orElseThrow();
		Course course = Course.createCourse(createdUser, meter);
		courseRepository.saveAndFlush(course);

		String filename = "/b612bicycle/courses/" + course.getId().toString();
		CSVWriter writer = new CSVWriter(new FileWriter(filename));
		for (List<Double> list : courseArray) {
			String[] data = {list.get(0).toString(), list.get(1).toString()};
			writer.writeNext(data);
		}
		writer.close();
		course.setArrayUrl(filename);
		courseRepository.save(course);
		return course.getId();
	}

	public List<List<Double>> loadCourseArray(Integer courseId) throws FileNotFoundException, IOException, CsvValidationException {
		Course course = courseRepository.findById(courseId).orElseThrow();

		String filename = course.getArrayUrl();
		CSVReader reader = new CSVReader(new FileReader(filename));
		List<List<Double>> courseArray = new ArrayList<>(); 
		
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			courseArray.add(Arrays.asList(Double.parseDouble(nextLine[0]), Double.parseDouble(nextLine[1])));
		}
		reader.close();
		return courseArray;
	}
}
