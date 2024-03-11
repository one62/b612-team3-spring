package b612.bicyclecommunity.repository;

import b612.bicyclecommunity.domain.team.Team;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.domain.userTeam.UserTeam;
import b612.bicyclecommunity.dto.team.res.SearchRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserTeamRepository extends JpaRepository<UserTeam, Integer> {

    /**
     * 팀 이름으로 count 하여 팀 내부의 인원이 몇 명인지 리턴한다.
     * @param name
     * @return
     */
    @Query("select count(ut) from UserTeam ut where ut.team.name=:name")
    Integer countByTeamName(String name);

    @Query("select ut from UserTeam ut where ut.team = :team and ut.user = :user")
    public abstract Boolean teamHasUser(@Param("team") Team team, @Param("user") User user);

    @Query("select new b612.bicyclecommunity.dto.team.res.SearchRes(ut.team.name, ut.team.comment, ut.team.address, ut.team.createdAt, ut.team.kind) from UserTeam ut where ut.user = :user")
    Page<SearchRes> findByUser(User user, Pageable pageable);

}
