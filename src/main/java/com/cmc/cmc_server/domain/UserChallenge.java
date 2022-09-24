package com.cmc.cmc_server.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ApiModel("초대된 사용자 정보")
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "idx", example = "1")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Story story;

    private String content;

    //성공여부
    @Builder.Default
    private boolean IsSuccess = false;

    //지목여부
    @Builder.Default
    private boolean nomination = false;

}
