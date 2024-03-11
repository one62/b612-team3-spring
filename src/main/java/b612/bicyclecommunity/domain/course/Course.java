package b612.bicyclecommunity.domain.course;

import b612.bicyclecommunity.domain.converter.DoubleListConverter;
import b612.bicyclecommunity.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


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

    // 코스 파일 경로
    @Setter
    private String fileUrl;

    // 코스 생성한 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User createdUser;

    // 코스 정보
    Double totalTravelDistance; // 총 이동한 거리, km
    @Convert(converter = DoubleListConverter.class) List<Double> startLatLng; // 시작 좌표 [위도,경도]
    @Convert(converter = DoubleListConverter.class) List<Double> endLatLng; // 마지막 지점 좌표 [위도,경도]
    @Convert(converter = DoubleListConverter.class) List<Double> centerLatLng; // 경로를 지도에 표시할때 중심 좌표
    @Convert(converter = DoubleListConverter.class) List<Double> southwestLatLng; // 지도의 남서쪽 좌표
    @Convert(converter = DoubleListConverter.class) List<Double> northeastLatLng; // 지도의 북동쪽 좌표
    Double zoom; // 지도 배율
    String name; // 코스 이름
    Boolean publicCourse; // 공개여부 (true면 공개 false면 비공개)


    // 초기값 필요한 값들은 모두 추가해야함
    public static Course createCourse(User createdUser, String name, 
    Double totalTravelDistance, List<Double> startLatLng, List<Double> endLatLng, List<Double> centerLatLng,
    List<Double> southwestLatLng, List<Double> northeastLatLng, Double zoom, Boolean publicCourse) {
        Course course = new Course();
        course.setCreatedUser(createdUser);
        course.setName(name);
        course.setTotalTravelDistance(totalTravelDistance);
        course.setStartLatLng(startLatLng);
        course.setEndLatLng(endLatLng);
        course.setCenterLatLng(centerLatLng);
        course.setSouthwestLatLng(southwestLatLng);
        course.setNortheastLatLng(northeastLatLng);
        course.setZoom(zoom);
        course.setPublicCourse(publicCourse);
        return course;
    }
}
