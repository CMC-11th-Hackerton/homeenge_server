package com.cmc.cmc_server.dto.Story;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "스토리 조회 응답")
public class GetStoryRes {

    @ApiModelProperty(value = "유저 닉네임", example = "베이")
    private String nickname;

    @ApiModelProperty(value = "스토리 이미지 파일", example = "abcd123.com")
    private List<String> images;
}
