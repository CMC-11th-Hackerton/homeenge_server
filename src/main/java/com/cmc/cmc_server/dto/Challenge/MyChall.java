package com.cmc.cmc_server.dto.Challenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyChall {
    @Builder.Default
    List<MadeChall> madeChalls = new ArrayList<>();

    @Builder.Default
    List<CurrChall> currChalls = new ArrayList<>();

}
