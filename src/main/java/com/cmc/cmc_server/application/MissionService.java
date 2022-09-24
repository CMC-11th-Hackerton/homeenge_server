package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.Mission;
import com.cmc.cmc_server.infra.ChallengeRepository;
import com.cmc.cmc_server.infra.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;

    public List<Mission> getMission() {
        return missionRepository.findAll();
    }

}
