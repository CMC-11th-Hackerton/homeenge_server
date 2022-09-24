package com.cmc.cmc_server.controllers;

import com.cmc.cmc_server.application.ChallengeService;
import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.domain.UserChallenge;
import com.cmc.cmc_server.dto.Challenge.ChallengeReq;
import com.cmc.cmc_server.dto.Challenge.GetNominationRes;
import com.cmc.cmc_server.dto.Challenge.RoomReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "미션 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @ApiOperation(value = "인기 순으로 챌린지 출력", notes = "홈 화면에 인기순으로 챌린지를 보여줍니다.")
    @GetMapping("/count")
    public List<Challenge> getChallenge(@RequestParam("id") Long id) {
        return challengeService.getCount(id);
    }

    @ApiOperation(value = "챌린지 방 생성", notes = "주어진 정보를 받아 챌린지 방 생성 API")
    @PostMapping
    public Challenge createChallenge(@RequestBody RoomReq roomReq) {
        return challengeService.createRoom(roomReq);
    }

    @ApiOperation(value = "모든 챌린지 방 불러오기", notes = "모든 챌린지 방을 불러오는 API")
    @GetMapping
    public List<Challenge> getRoom() {
        return challengeService.getRoom();
    }

    @ApiOperation(value = "특정 챌린지 방 불러오기", notes = "특정 챌린지 방을 불러오는 API")
    @GetMapping("/room")
    public Challenge getRoom(@RequestParam @ApiParam(value = "방 인덱스") Long id) {
        return challengeService.getRoom(id);
    }

    @ApiOperation(value = "챌린지 방 신청하기", notes = "챌린지 방에 참여하는 API, 정상적으로 참여한 경우 true를 반환합니다.")
    @PostMapping("/enter")
    public ResponseEntity<Void> enterRoom(@RequestBody ChallengeReq challengeReq) {
        challengeService.enterRoom(challengeReq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 챌린지 지목 리스트 API*
     * @param challengeId
     * @return List<GetNominationRes></GetNominationRes>
     */
    @ApiOperation(value = "챌린지 지목 리스트", notes = "챌린지 지목을 받지 않은 유저 리스트를 반환합니다.")
    @GetMapping("/nominate/{challengeId}")
    public ResponseEntity<?> getNomination(@PathVariable Long challengeId) {
        try {
            List<GetNominationRes> nominationList = challengeService.getNomination(challengeId);
            return ResponseEntity.ok().body(nominationList);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
