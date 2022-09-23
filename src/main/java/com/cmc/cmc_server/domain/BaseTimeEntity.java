package com.cmc.cmc_server.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 공통의 생성 시각, 수정 시각을 사용하기 위한 엔터티 입니다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false)
    @ApiModelProperty(value = "생성 시각", example = "2022-03-03T18:43:52.9694505")
    private LocalDateTime createAt;

    @LastModifiedDate
    @ApiModelProperty(value = "업데이트 시각", example = "2022-03-03T18:43:52.9694505")
    private LocalDateTime updateAt;

}
