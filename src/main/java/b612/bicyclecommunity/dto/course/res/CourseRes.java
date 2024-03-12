package b612.bicyclecommunity.dto.course.res;


import b612.bicyclecommunity.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CourseRes {
	/*
	 * 자신이 새로 생성한 코스만 조회
	 * 	createdUser가 자신이고, original이 true인 경우
	 * 다른 사람의 코스를 따라 주행한 코스도 조회
	 * 	createdUser가 자신이 아니고, original이 true이며, courseuser에 자신의 id가 있는 경우
	 * 자신이 주행한 적 없는 공개 코스도 조회
	 * 	createdUser가 자신이 아니고, original이 true이며, publicCourse가 true인 경우
	 */
	
	String name; // 코스 이름
	User createdUser; // 코스 생성한 사용자
	Boolean original; // 자기 코스인지, 다른 사람이 생성한 코스를 따라 주행했는지
	Double totalTravelDistance; // 총 이동한 거리
	Double zoom; // 지도 배율
	Boolean publicCourse; // 공개여부 (true면 공개 false면 비공개)

	List<Double> startLatLng; // 시작 좌표 [위도,경도]
	List<Double> endLatLng; // 마지막 지점 좌표 [위도,경도]
	List<Double> centerLatLng; // 경로를 지도에 표시할때 중심 좌표
	List<Double> southwestLatLng; // 지도의 남서쪽 좌표
	List<Double> northeastLatLng; // 지도의 북동쪽 좌표

	int avgElapsedTime; // 평균 주행 시간
	Double avgRating; // 평균 별점
	Double avgDifficulty; // 평균 난이도
	int reviewCount; // 리뷰 개수
	


}
