package b612.bicyclecommunity.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final Set<DriveSession> DriveSessionSet = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("Connected"));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //DriveMessage 파싱
        DriveMessage driveMessage = objectMapper.readValue(message.getPayload(), DriveMessage.class);

        Optional<DriveSession> optionalDriveSession = DriveSessionSet.stream()
                .filter(driveSession -> session.equals(driveSession.getSession()))
                .findFirst();

        if (optionalDriveSession.isPresent()) {
            // DriveSession 을 찾은 경우 해당 세션의 값을 업데이트
            DriveSession driveSession = optionalDriveSession.get();
            driveSession.setLocate1(driveMessage.getLocate1());
            driveSession.setLocate2(driveMessage.getLocate2());
        } else {
            // DriveSession 을 찾지 못한 경우 새로운 세션 생성 및 추가
            DriveSession newDriveSession = new DriveSession(
                    driveMessage.getId(),
                    session,
                    driveMessage.getTeam(),
                    driveMessage.getLocate1(),
                    driveMessage.getLocate2()
            );
            DriveSessionSet.add(newDriveSession);
        }

        List<WebSocketSession> sessionsByTeam = new ArrayList<>();

        for (DriveSession driveSession : DriveSessionSet) {
            if (driveSession.getTeam().equals(driveMessage.getTeam())) {
                sessionsByTeam.add(driveSession.getSession());
            }
        }

        List<DriveSession> specificTeamUsers = DriveSessionSet.stream()
                .filter(driveSession -> driveSession.getTeam().equals(driveMessage.getTeam()))
                .map(driveSession -> new DriveSession(
                        driveSession.getId(),
                        null, // SessionID 제외
                        driveSession.getTeam(),
                        driveSession.getLocate1(),
                        driveSession.getLocate2()
                )).toList();

        String json = objectMapper.writeValueAsString(specificTeamUsers);

        for (WebSocketSession teamSession : sessionsByTeam) {
            teamSession.sendMessage(new TextMessage(json));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        DriveSessionSet.removeIf(driveSession -> driveSession.getSession().equals(session));
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class DriveSession{
    private String id;
    private WebSocketSession session;
    private String team;
    private Double Locate1;
    private Double Locate2;
}
