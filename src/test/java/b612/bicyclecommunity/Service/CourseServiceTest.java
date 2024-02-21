// package b612.bicyclecommunity.Service;

// import org.junit.jupiter.api.MethodOrderer;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestMethodOrder;
// import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import org.springframework.data.util.Pair;
// import java.util.List;
// import java.util.ArrayList;

// import b612.bicyclecommunity.domain.user.LoginKind;
// import b612.bicyclecommunity.domain.user.User;
// import b612.bicyclecommunity.repository.UserRepository;
// import b612.bicyclecommunity.service.CourseService;
// import jakarta.transaction.Transactional;

// @SpringBootTest
// @Transactional
// public class CourseServiceTest {
	
// 	@Autowired CourseService courseService;
// 	@Autowired UserRepository userRepository;

// 	@Test
// 	public void saveCourseTest() throws Exception {
// 		List<Pair<Double, Double>> courseArray = new ArrayList<>();
// 		courseArray.add(Pair.of(1.0, 1.0));
// 		courseArray.add(Pair.of(3.0, 2.0));
// 		User user = new User("1", LoginKind.KaKao);
// 		userRepository.save(user);
// 		Integer i = courseService.saveCourse("1", 5, courseArray);

// 		List<Pair<Double, Double>> courseArray2;
// 		courseArray2 = courseService.loadCourseArray(i);
// 		System.out.println(courseArray2);
// 	}

// }
