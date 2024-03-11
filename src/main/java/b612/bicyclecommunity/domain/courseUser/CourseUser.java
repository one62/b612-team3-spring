package b612.bicyclecommunity.domain.courseUser;

import b612.bicyclecommunity.domain.course.Course;
import b612.bicyclecommunity.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "course_user")
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_user_id")
    private Integer id;

    String startTime; // 시작시간, Iso8601 포맷
    String endTime; // 끝난시간, Iso8601 포맷
    int elapsedTime; // 총 걸린 시간, second

    int rating; //별점(1~5)
    int difficulty; //난이도(1~5)
    String review;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public static CourseUser createCourseUser(String startTime, String endTime, int elapsedTime,
    int rating, int difficulty, String review, User user, Course course) {
        CourseUser cu = new CourseUser();
        cu.setStartTime(startTime);
        cu.setEndTime(endTime);
        cu.setElapsedTime(elapsedTime);
        cu.setRating(rating);
        cu.setDifficulty(difficulty);
        cu.setReview(review);
        cu.setUser(user);
        cu.setCourse(course);
        return cu;
    }
}
