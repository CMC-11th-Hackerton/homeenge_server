package com.cmc.cmc_server.dto.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO는 계층간 데이터 교환을 위한 객체
 * DB의 데이터를 Service나 Controller 등으로 보낼 때 사용하는 객체 입니다.
 */

@Builder
@Getter
@AllArgsConstructor
@ApiModel("사용자 결과 정보")
public class UserResultData {

    @ApiModelProperty(value = "사용자 인덱스", example = "1")
    private String id;

    @ApiModelProperty(value = "사용자 닉네임", example = "케빈")
    private String nickname;

    @ApiModelProperty(value = "사용자 프로필 사진", example = "image.com")
    private String imageUrl;

}
