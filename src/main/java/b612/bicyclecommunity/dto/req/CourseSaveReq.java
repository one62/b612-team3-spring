package b612.bicyclecommunity.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CourseSaveReq {
	
	String startTime; // 시작시간, Iso8601 포맷
	String endTime; // 끝난시간, Iso8601 포맷
	int elapsedTime; // 총 걸린 시간, second
	Double totalTravelDistance; // 총 이동한 거리, km
	String encodedPolyline; // 인코딩된 경로
	List<Double> startLatLng; // 시작 좌표 [위도,경도]
	List<Double> endLatLng; // 마지막 지점 좌표 [위도,경도]
	List<Double> centerLatLng; // 경로를 지도에 표시할때 중심 좌표
	List<Double> southwestLatLng; // 지도의 남서쪽 좌표
	List<Double> northeastLatLng; // 지도의 북동쪽 좌표
	double zoom; // 지도 배율
	String name; // 코스 이름
	int rating; // 별점 (1~5)
	int difficulty; // 난이도 (1~5)
	String review; // 리뷰
	Boolean publicCourse; // 공개여부 (true면 공개 false면 비공개)

}
