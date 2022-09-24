package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.domain.Mission;
import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.domain.UserChallenge;
import com.cmc.cmc_server.dto.Challenge.ChallengeReq;
import com.cmc.cmc_server.dto.Challenge.RoomReq;
import com.cmc.cmc_server.errors.CustomException;
import com.cmc.cmc_server.errors.ErrorCode;
import com.cmc.cmc_server.infra.ChallengeRepository;
import com.cmc.cmc_server.infra.MissionRepository;
import com.cmc.cmc_server.infra.UserChallengeRepository;
import com.cmc.cmc_server.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChallengeService {

    private final MissionRepository missionRepository;
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;
    private final UserChallengeRepository userChallengeRepository;

    public List<Challenge> getCount(Long id) {
        return challengeRepository.findTop5ByOwnerIdOrderByCountsDesc(id);
    }

    public Challenge createRoom(RoomReq roomReq) {
        User user = userRepository.findById(roomReq.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Mission mission = missionRepository.getById(roomReq.getMissionId());

        String input = "20220925"+roomReq.getEndTime();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime ldt = LocalDateTime.parse(input.substring(0, 14), f);

        Challenge challenge = Challenge.builder()
                .owner(user)
                .title(roomReq.getContent())
                .content(roomReq.getContent())
                .counts(roomReq.getCounts())
                .mission(mission)
                .finishTime(ldt)
                .build();

        return challengeRepository.save(challenge);
    }

    public List<Challenge> getRoom() {
        return challengeRepository.findAll();
    }

    public Challenge getRoom(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_MATCHED));
    }

    public void enterRoom(ChallengeReq challengeReq) {
        User user = userRepository.getById(challengeReq.getUserId());
        Challenge challenge = challengeRepository.getById(challengeReq.getRoomId());
        challenge.addCurrCounts();

        userChallengeRepository.save(
                UserChallenge.builder().challenge(challenge).user(user)
                        .build());

    }

}
