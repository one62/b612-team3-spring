package b612.bicyclecommunity.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import org.springframework.data.util.Pair;

@Data
@AllArgsConstructor
public class CourseSaveReq {
	
	private Integer meter;
	private List<Pair<Double, Double>> courseArray;
	
}
