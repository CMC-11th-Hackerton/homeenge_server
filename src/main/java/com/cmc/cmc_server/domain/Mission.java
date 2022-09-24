package com.cmc.cmc_server.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ApiModel("미션 정보")
public class Mission {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ApiModelProperty(value = "idx", example = "1")
    private Long id;

    @ApiModelProperty(value = "미션 이름", example = "양치하기")
    private String title;

    @ApiModelProperty(value = "미션 정원", example = "5")
    private Integer personnel;

}
