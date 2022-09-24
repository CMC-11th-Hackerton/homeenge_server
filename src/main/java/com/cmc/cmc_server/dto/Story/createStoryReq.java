package com.cmc.cmc_server.dto.Story;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "스토리 생성")
public class createStoryReq {

    @ApiModelProperty(value = "사용자 id", example = "1")
    private Long id;

    @ApiModelProperty(value = "스토리 이미지 파일", example = "image.com")
    private List<MultipartFile> imageFiles;
}


