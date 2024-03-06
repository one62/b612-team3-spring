package b612.bicyclecommunity.repository;

import b612.bicyclecommunity.domain.team.Team;
import b612.bicyclecommunity.dto.team.res.SearchRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findByName(String s);

    @Query("select new b612.bicyclecommunity.dto.team.res.SearchRes(t.name, t.comment, t.address, t.createdAt, t.kind) from Team t where t.name like %:keyword%")
    Page<SearchRes> searchByName(String keyword, Pageable pageable);
}
