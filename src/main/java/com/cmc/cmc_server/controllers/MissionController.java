package com.cmc.cmc_server.controllers;

import com.cmc.cmc_server.application.MissionService;
import com.cmc.cmc_server.domain.Mission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "미션 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService challengeService;

    @ApiOperation(value = "미션 리스트 불러오기", notes = "미션 리스트 불러오는 API")
    @GetMapping
    public List<Mission> getMission() {
        return challengeService.getMission();
    }

}
