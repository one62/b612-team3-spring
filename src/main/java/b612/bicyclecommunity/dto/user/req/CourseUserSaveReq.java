package b612.bicyclecommunity.dto.user.req;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseUserSaveReq {

    Integer courseId;

	String startTime; // 시작시간, Iso8601 포맷
	String endTime; // 끝난시간, Iso8601 포맷
	int elapsedTime; // 총 걸린 시간, second
	int rating; // 별점 (1~5)
	int difficulty; // 난이도 (1~5)
	String review; // 리뷰
}
