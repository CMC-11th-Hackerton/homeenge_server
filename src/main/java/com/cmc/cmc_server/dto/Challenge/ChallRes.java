package com.cmc.cmc_server.dto.Challenge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@ApiModel("챌린지 정보")
public class ChallRes {

    @ApiModelProperty(value = "챌린지 인덱스", example = "1")
    private Long id;

    @ApiModelProperty(value = "미션 이름", example = "8시에 일어나기")
    private String missionName;

    @ApiModelProperty(value = "챌린지 제목", example = "8시에 일어나기")
    private String challengeName;

    @ApiModelProperty(value = "총 인원 수", example = "15")
    private Long counts;

    @ApiModelProperty(value = "참여 인원 수", example = "4")
    private Long currCounts;

    @ApiModelProperty(value = "모집 마감 시간(시간분초)", example = "2021-11-02 HH:MM:SS")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "단계 숫자", example = "1")
    private Integer step;

}
