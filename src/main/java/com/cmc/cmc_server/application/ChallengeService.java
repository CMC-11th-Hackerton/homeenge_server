package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.dto.Challenge.ChallengeReq;
import com.cmc.cmc_server.infra.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public List<Challenge> getCount(Long id){
        return challengeRepository.findAllByOwnerIdOrderByCountsDesc(id);
    }
}
