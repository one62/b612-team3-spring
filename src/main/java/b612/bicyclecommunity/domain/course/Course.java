package b612.bicyclecommunity.domain.course;

import b612.bicyclecommunity.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    /** 기본키
     * 타입 : Integer
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;

    /**
     *
     */
    @Setter
    @Column(name = "course_array_url")
    private String arrayUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_difficulty")
    private Difficulty difficulty;

    // 경로 거리
    @Column(name = "course_meter")
    private Integer meter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User createdUser;

    // 초기값 필요한 값들은 모두 추가해야함
    public static Course createCourse(User user, Integer meter) {
        Course course = new Course();
        course.setCreatedUser(user);
        course.setMeter(meter);
        return course;
    }
}
