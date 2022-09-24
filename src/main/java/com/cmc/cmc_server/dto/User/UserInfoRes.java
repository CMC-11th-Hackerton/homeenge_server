package com.cmc.cmc_server.dto.User;

import com.cmc.cmc_server.dto.Challenge.ChallengeRes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@ApiModel("사용자 가입 정보")
public class UserInfoRes {

    // 유저 개인정보
    @ApiModelProperty(value = "사용자 이메일", example = "test@gmail.com")
    private String email;

    @ApiModelProperty(value = "사용자 닉네임", example = "베이")
    private String nickname;

    @ApiModelProperty(value = "사용자 포인트", example = "10")
    private int points;

    // 참여 중인 챌린지 리스트
    private List<ChallengeRes> challengeList;

}
