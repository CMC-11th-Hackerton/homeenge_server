package com.cmc.cmc_server.dto.Story;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "스토리 조회 요청")
public class GetStoryReq {

    @ApiModelProperty(value = "사용자 id", example = "1")
    private long userId;

    @ApiModelProperty(value = "챌린지 id", example = "1")
    private long challengeId;

}
