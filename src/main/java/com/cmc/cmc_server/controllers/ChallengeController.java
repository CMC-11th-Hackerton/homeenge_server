package com.cmc.cmc_server.controllers;

import com.cmc.cmc_server.application.ChallengeService;
import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.domain.UserChallenge;
import com.cmc.cmc_server.dto.Challenge.*;
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
    public List<Challenge> getChallenge(@RequestParam("id") @ApiParam(value = "유저 인덱스") Long id) {
        return challengeService.getCount(id);
    }

    @ApiOperation(value = "챌린지 방 생성", notes = "주어진 정보를 받아 챌린지 방 생성 API")
    @PostMapping
    public ResponseEntity<Void> createChallenge(@RequestBody RoomReq roomReq) {
        challengeService.createRoom(roomReq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "챌린지 방 신청하기", notes = "챌린지 방에 참여하는 API, response로 200을 반환합니다.")
    @PostMapping("/enter")
    public ResponseEntity<Void> enterToRoom(@RequestBody ChallengeReq challengeReq) {
        challengeService.enterRoom(challengeReq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "단계 별 챌린지 방 불러오기", notes = "모든 챌린지 방을 불러오는 API")
    @GetMapping
    public List<ChallRes> getRoomByStep(@RequestParam @ApiParam(value = "단계 숫자") Integer step) {
        return challengeService.getRoomByStep(step);
    }

    @ApiOperation(value = "특정 챌린지 방 불러오기", notes = "특정 챌린지 방을 불러오는 API")
    @GetMapping("/room")
    public RoomRes getRoom(@RequestParam @ApiParam(value = "방 인덱스") Long id) {
        return challengeService.getRoom(id);
    }

    @ApiOperation(value = "나의 챌린지 전체 불러오기", notes = "모집한 챌린지와 참여중인 챌린지를 불러오는 API")
    @GetMapping("/myChall")
    public MyChall getMyAllChallenge(@RequestParam @ApiParam(value = "유저 인덱스") Long id) {
        return challengeService.getMyAllChallenge(id);
    }

    @ApiOperation(value = "나의 진행중인 챌린지 불러오기", notes = "진행중인 챌린지를 불러오는 API")
    @GetMapping("/myCurrChall")
    public MyChall getMyCurrChallenge(@RequestParam @ApiParam(value = "유저 인덱스") Long id) {
        return challengeService.getMyCurrChallenge(id);
    }

    @ApiOperation(value = "나의 종료한 챌린지 불러오기", notes = "종료된 챌린지를 불러오는 API")
    @GetMapping("/myEndChall")
    public MyChall getMyEndChallenge(@RequestParam @ApiParam(value = "유저 인덱스") Long id) {
        return challengeService.getMyEndChallenge(id);
    }

    @ApiOperation(value = "챌린지 방 신청하기", notes = "챌린지 방에 참여하는 API, 정상적으로 참여한 경우 true를 반환합니다.")
    @PostMapping("/enter")
    public ResponseEntity<Void> enterRoom(@RequestBody ChallengeReq challengeReq) {
        challengeService.enterRoom(challengeReq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 챌린지 지목 리스트 API*
     *
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

    @ApiOperation(value = "챌린지 지목", notes = "챌린지 지목을 받은 유저의 nomination값을 true로 바꿉니다.")
    @PostMapping("/nominate")
    public void nominationUser(@RequestBody NominateReq nominateReq) {
        challengeService.nominationUser(nominateReq);
    }

}
