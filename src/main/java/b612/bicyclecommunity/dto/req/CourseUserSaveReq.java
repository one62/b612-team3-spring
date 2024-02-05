package b612.bicyclecommunity.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CourseUserSaveReq {

    private LocalDateTime start;
    private LocalDateTime end;
    private Integer rate;
    private String comment;
    private Double averageSpeed;
    private Double restMinute;

    private String courseName;
}
