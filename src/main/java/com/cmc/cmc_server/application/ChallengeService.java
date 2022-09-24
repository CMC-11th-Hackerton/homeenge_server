package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.dto.Challenge.RoomReq;
import com.cmc.cmc_server.errors.CustomException;
import com.cmc.cmc_server.errors.ErrorCode;
import com.cmc.cmc_server.infra.ChallengeRepository;
import com.cmc.cmc_server.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    public List<Challenge> getCount(Long id) {
        return challengeRepository.findAllByOwnerIdOrderByCountsDesc(id);
    }

    @Transactional
    public Challenge createRoom(RoomReq roomReq) {
        User user = userRepository.findById(roomReq.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Challenge challenge = Challenge.builder()
                .owner(user)
                .title(roomReq.getContent())
                .content(roomReq.getContent())
                .counts(roomReq.getCounts())
                .build();

        return challengeRepository.save(challenge);
    }
}
