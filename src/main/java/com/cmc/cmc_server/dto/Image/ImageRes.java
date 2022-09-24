package com.cmc.cmc_server.dto.Image;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "이미지 저장 경로")
@Getter
@Builder
@AllArgsConstructor
public class ImageRes {

    @ApiModelProperty(value = "해당 게시글에 저장된 이미지 경로")
    @Builder.Default
    private List<String> imageUrl = new ArrayList<>();

}
