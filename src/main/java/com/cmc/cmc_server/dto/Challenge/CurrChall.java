package com.cmc.cmc_server.dto.Challenge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@ApiModel("참여중인 챌린지 정보")
public class CurrChall {
    @ApiModelProperty(value = "챌린지 인덱스", example = "1")
    private Long id;

    @ApiModelProperty(value = "첼린지(미션) 이미지", example = "image.com")
    private String imageUrl;

    @ApiModelProperty(value = "미션 내용", example = "8시에 일어나서 이불개기")
    private String missionName;

    @ApiModelProperty(value = "진행 여부", example = "true")
    private boolean finished;

}
