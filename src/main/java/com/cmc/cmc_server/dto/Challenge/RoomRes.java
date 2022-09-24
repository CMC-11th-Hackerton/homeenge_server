package com.cmc.cmc_server.dto.Challenge;

import com.cmc.cmc_server.dto.User.UserResultData;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class RoomRes {

    @ApiModelProperty(value = "첼린지 이름", example = "밥 먹기 챌린지")
    private String title;

    @ApiModelProperty(value = "미션 이름", example = "8시에 일어나기")
    private String missionName;

    @ApiModelProperty(value = "총 인원 수", example = "15")
    private Long counts;

    @ApiModelProperty(value = "참여 인원 수", example = "4")
    private Long currCounts;

    @ApiModelProperty(value = "참여 인원", example = "image.com")
    private List<UserResultData> userResultData = new ArrayList<>();

    @ApiModelProperty(value = "방장님의 말", example = "챌린지 하실 분")
    private String content;

    @ApiModelProperty(value = "모집 마감 시간(시간분초)", example = "2021-11-02 yy:mm::dd")
    private LocalDateTime endTime;
}
