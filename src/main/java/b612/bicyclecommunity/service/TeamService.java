package b612.bicyclecommunity.service;

import b612.bicyclecommunity.domain.team.Kind;
import b612.bicyclecommunity.domain.team.Team;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.domain.userTeam.Level;
import b612.bicyclecommunity.domain.userTeam.UserTeam;
import b612.bicyclecommunity.dto.team.res.InfoRes;
import b612.bicyclecommunity.dto.team.res.SearchRes;
import b612.bicyclecommunity.repository.TeamRepository;
import b612.bicyclecommunity.repository.UserRepository;
import b612.bicyclecommunity.repository.UserTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserTeamRepository userTeamRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(String name, String comment, String address, LocalDateTime createdAt, Kind kind, String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));
        Team team = new Team(name, comment, address, createdAt, kind);
        UserTeam userTeam = new UserTeam(createdAt, Level.MASTER, user, team);

        teamRepository.save(team);
        userTeamRepository.save(userTeam);
    }

    @Transactional
    public InfoRes info(String name) {

        Team team = teamRepository.findByName(name).orElseThrow(() -> new RuntimeException("해당 팀 없음"));

        return new InfoRes(
            team.getName(),
            team.getComment(),
            team.getAddress(),
            team.getCreatedAt(),
            team.getKind(),
            userTeamRepository.countByTeamName(name)
        );
    }

    @Transactional
    public void join(String name, LocalDateTime joinedAt, String userId) {

        Team team = teamRepository.findByName(name).orElseThrow(() -> new RuntimeException("해당 팀 없음"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저 없음"));

        if(userTeamRepository.teamHasUser(team,user)){
            throw new RuntimeException("해당 유저 이미 존재함");
        }

        UserTeam userTeam = new UserTeam(
                joinedAt,
                Level.MEMBER,
                user,
                team
        );

        userTeamRepository.save(userTeam);
    }

    @Transactional
    public List<SearchRes> search(String kind, String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        if(kind.equals("name")){
            Page<SearchRes> searchRes = teamRepository.searchByName(keyword, pageable);
            return searchRes.getContent();
        } else{
            return null;
        }
    }
}
