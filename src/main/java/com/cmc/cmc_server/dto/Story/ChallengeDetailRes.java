package com.cmc.cmc_server.dto.Story;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "챌린지 정보 응답")
public class ChallengeDetailRes {
    @ApiModelProperty(value = "챌린지 제목", example = "8시에 일어나기")
    private String title;

    @ApiModelProperty(value = "인원 수", example = "10")
    private Long counts;

    @ApiModelProperty(value = "찜", example = "1")
    private Integer likes;

    @ApiModelProperty(value = "찜", example = "1")
    private String lastTime;

    @ApiModelProperty(value = "유저 스토리", example = "")
    private List<UserStoryRes> userStoryResList;
}

