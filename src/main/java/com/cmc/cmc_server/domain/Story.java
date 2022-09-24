package com.cmc.cmc_server.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ApiModel("스토리 정보")
public class Story extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "스토리 id", example = "1")
    private Long storyId;

    @ApiModelProperty(value = "사용자 id", example = "1")
    private Long userId;

    @OneToOne
    private Challenge challenge;

    @ApiModelProperty(value = "스토리 이미지 url", example = "image.com")
    private String imageUrl;

    @ApiModelProperty(value = "스토리 신고 수", example = "3")
    private int report;

    @Builder
    public Story(Long userId, Challenge challenge, String imageUrl) {
        this.userId = userId;
        this.challenge = challenge;
        this.imageUrl = imageUrl;
    }
}
