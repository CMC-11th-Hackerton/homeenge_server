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
public class RoomReq {

    @ApiModelProperty(value = "리더 인덱스", example = "1")
    private Long id;

    @ApiModelProperty(value = "첼린지 이름", example = "밥 먹기 챌린지")
    private String title;

    @ApiModelProperty(value = "내용", example = "챌린지 하실 분")
    private String content;

    @ApiModelProperty(value = "인원 수", example = "4")
    private Long counts;

}
