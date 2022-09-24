package com.cmc.cmc_server.controllers;

import com.cmc.cmc_server.application.ChallengeService;
import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.dto.Challenge.ChallengeReq;
import com.cmc.cmc_server.dto.Session.SignupReq;
import com.cmc.cmc_server.dto.Session.SignupRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


}
