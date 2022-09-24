package com.cmc.cmc_server.dto.Challenge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@ApiModel("지목할 사람 리스트")
public class GetNominationRes {

    @ApiModelProperty(value = "유저 id", example = "1")
    private Long id;

    @ApiModelProperty(value = "유저 닉네임", example = "베이")
    private String nickname;
}
