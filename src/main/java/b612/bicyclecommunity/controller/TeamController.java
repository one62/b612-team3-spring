package b612.bicyclecommunity.controller;

import b612.bicyclecommunity.domain.team.Team;
import b612.bicyclecommunity.dto.team.req.JoinReq;
import b612.bicyclecommunity.dto.team.req.SaveReq;
import b612.bicyclecommunity.dto.team.res.InfoRes;
import b612.bicyclecommunity.dto.team.res.SearchRes;
import b612.bicyclecommunity.global.security.UserDetailsImpl;
import b612.bicyclecommunity.service.TeamService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/team")
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody b612.bicyclecommunity.dto.team.req.SaveReq saveReq){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        teamService.save(
                saveReq.getName(),
                saveReq.getComment(),
                saveReq.getAddress(),
                saveReq.getCreatedAt(),
                saveReq.getKind(),
                userDetails.getUserId()
        );

        return ResponseEntity.ok().body("팀 생성 완료");

    }

    @GetMapping("/info") // info?name='required name'
    public ResponseEntity<?> info(@RequestParam("name") @NotBlank String name){

        InfoRes infoRes = teamService.info(name);
        return ResponseEntity.ok().body(infoRes);

    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody b612.bicyclecommunity.dto.team.req.JoinReq joinReq){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        teamService.join(
                joinReq.getName(),
                joinReq.getJoinedAt(),
                userDetails.getUserId()
        );

        return ResponseEntity.ok().body(joinReq.getName()+ "에 가입완료");
    }

    @GetMapping("/search") //search?kind=kind&keyword=keyword&page=page&size=size
    public ResponseEntity<?> search(@RequestParam("kind") @NotBlank String kind,
                                    @RequestParam("keyword") @NotBlank String keyword,
                                    @RequestParam("page") int page,
                                    @RequestParam("size") int size) {
        List<SearchRes> teams = teamService.search(kind, keyword, page, size);
        return ResponseEntity.ok().body(teams);
    }
}
