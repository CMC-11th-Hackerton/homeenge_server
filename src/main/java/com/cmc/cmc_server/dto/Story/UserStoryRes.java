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
public class UserStoryRes {
    @ApiModelProperty(value = "사용자 id", example = "1")
    private Long userId;

    @ApiModelProperty(value = "스토리 인증", example = "true")
    private boolean isSuccess;

    @ApiModelProperty(value = "유저 프로필", example = "image.com")
    private String imageUrl;
}
