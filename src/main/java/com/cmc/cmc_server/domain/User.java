package com.cmc.cmc_server.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

/**
 * User 도메인
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ApiModel("사용자 정보")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "사용자 idx", example = "1")
    private Long id;

    @ApiModelProperty(value = "사용자 이메일", example = "kmw106933@naver.com")
    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @ApiModelProperty(value = "사용자 패스워드", example = "abc123")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 100)
    private String password;

    @ApiModelProperty(value = "사용자 닉네임", example = "케빈케빈")
    private String nickname;

    @ApiModelProperty(value = "사용자 포인트", example = "15")
    @Builder.Default
    private Integer points = 0;

    @ApiModelProperty(value = "사용자 프로필 사진", example = "img.jpg")
    private String imageUrl;



}
