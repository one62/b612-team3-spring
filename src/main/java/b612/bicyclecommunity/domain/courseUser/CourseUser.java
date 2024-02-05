package b612.bicyclecommunity.domain.courseUser;

import b612.bicyclecommunity.domain.course.Course;
import b612.bicyclecommunity.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "course_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_user_id")
    private Integer id;

    @Column(name = "course_user_start")
    private LocalDateTime start;

    @Column(name = "course_user_end")
    private LocalDateTime end;

    @Column(name = "course_user_rate")
    private Integer rate;

    @Column(name = "course_user_comment")
    private String comment;

    @Column(name = "course_user_average_spped")
    private Double averageSpeed;

    @Column(name = "course_user_rest_minute")
    private Double restMinute;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /*save 에서 사용하는 생성자*/
    public CourseUser(LocalDateTime start,
               LocalDateTime end,
               Integer rate,
               String comment,
               Double averageSpeed,
               Double restMinute,
               User user,
               Course course){
        this.start = start;
        this.end = end;
        this.rate = rate;
        this.comment = comment;
        this.averageSpeed = averageSpeed;
        this.restMinute = restMinute;
        this.user = user;
        this.course = course;
    }
}
