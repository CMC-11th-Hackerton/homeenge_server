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
@ApiModel(description = "스토리 신고")
public class ReportStoryReq {

    @ApiModelProperty(value = "사용자 id", example = "1")
    private long userId;

    @ApiModelProperty(value = "스토리 id", example = "1")
    private long storyId;

}
