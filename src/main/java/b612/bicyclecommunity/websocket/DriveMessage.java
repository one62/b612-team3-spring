package b612.bicyclecommunity.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriveMessage {
    //개인 ID
    private String id;
    // team 이름
    private String team;
    //현재 위치
    private Double locate1;
    private Double locate2;
}
