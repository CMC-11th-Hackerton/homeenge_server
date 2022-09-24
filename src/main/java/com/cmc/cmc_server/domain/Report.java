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
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ApiModel("신고 정보")
public class Report {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "신고 idx", example = "1")
    private Long reportId;

    @ApiModelProperty(value = "사용자 idx", example = "1")
    private Long userId;

    @ApiModelProperty(value = "스토리 idx", example = "1")
    private Long storyId;

    @Builder
    public Report(Long userId, Long storyId) {
        this.userId = userId;
        this.storyId = storyId;
    }
}
