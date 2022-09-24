package com.cmc.cmc_server.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ApiModel("방 정보")
public class Challenge extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "idx", example = "1")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @OneToOne(fetch = FetchType.EAGER)
    private Mission mission;

    private String title;

    private String content;

    private String imageUrl;

    // 총 인원수
    @Builder.Default
    private Long counts = 0L;

    // 현재 참여 수
    @Builder.Default
    private Long currCounts = 0L;

    @Builder.Default
    private boolean finished = false;

    @Builder.Default
    private Integer step = 1;

    private LocalDateTime finishTime;

    @Builder.Default
    private Integer likes = 0;

    public void addCurrCounts() {
        currCounts += 1;
    }
}
