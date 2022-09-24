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
@ApiModel("잔디 정보")
public class Daily extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "idx", example = "1")
    private Long id;

    private boolean success;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //파이팅

}
