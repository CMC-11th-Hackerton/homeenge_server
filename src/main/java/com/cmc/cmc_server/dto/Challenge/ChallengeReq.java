package com.cmc.cmc_server.dto.Challenge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@ApiModel("방 입장 요청 정보")
public class ChallengeReq {
    @ApiModelProperty(value = "유저 인덱스", example = "1")
    private Long userId;

    @ApiModelProperty(value = "방 인덱스", example = "1")
    private Long roomId;
}
