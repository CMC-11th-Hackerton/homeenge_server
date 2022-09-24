package com.cmc.cmc_server.dto.Challenge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@ApiModel("챌린지 정보")
public class ChallengeRes {

    @ApiModelProperty(value = "챌린지 방 id", example = "1")
    private Long challengeId; // 챌린지 방 id

    @ApiModelProperty(value = "챌린지 미션 내용", example = "세수하기")
    private String title; // 미션
}
