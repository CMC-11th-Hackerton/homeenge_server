package com.cmc.cmc_server.dto.Daily;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@ApiModel("데일리 정보")
public class DailyRes {

    private LocalDateTime date;
    private boolean success;
    
}
