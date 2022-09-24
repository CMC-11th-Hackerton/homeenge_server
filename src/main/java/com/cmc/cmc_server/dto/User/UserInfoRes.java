package com.cmc.cmc_server.dto.User;

import com.cmc.cmc_server.dto.Daily.DailyRes;
import io.swagger.annotations.ApiModel;
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
    private String email;
    private String nickname;

    // 챌린지 정보
    private Long challengeId; // 챌린지 방 id
    private String title; // 미션

    // 데일리 리스트 정보 (한 달)
    private List<DailyRes> dailyList;

}
