package com.cmc.cmc_server.dto.Challenge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@ApiModel("사용자 요청 정보")
public class ChallengeReq {

    @ApiModelProperty(value = "사용자 인덱스", example = "1")
    private Long id;

}
