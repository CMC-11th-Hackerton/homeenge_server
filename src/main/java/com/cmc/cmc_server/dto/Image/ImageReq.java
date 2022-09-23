package com.cmc.cmc_server.dto.Image;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "이미지 업로드 모델")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageReq {

    @ApiModelProperty(value = "이미지 파일", required = false)
    private List<MultipartFile> imageFiles = new ArrayList<>();

}
