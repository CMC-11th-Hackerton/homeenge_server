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
    private Long leaderId;

    @ApiModelProperty(value = "단계", example = "1")
    private Long step;

    @ApiModelProperty(value = "첼린지 이름", example = "밥 먹기 챌린지")
    private String title;

    @ApiModelProperty(value = "미션 인덱스", example = "1")
    private Long missionId;

    @ApiModelProperty(value = "인원 수", example = "4")
    private Long counts;

    @ApiModelProperty(value = "방장님의 한 마디", example = "화이팅!")
    private String content;

    @ApiModelProperty(value = "모집 마감 시간(시간분초)", example = "180808")
    private String endTime;

}
