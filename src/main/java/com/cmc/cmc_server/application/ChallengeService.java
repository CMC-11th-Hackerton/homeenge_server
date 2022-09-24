package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.domain.UserChallenge;
import com.cmc.cmc_server.dto.Challenge.ChallengeReq;
import com.cmc.cmc_server.dto.Challenge.RoomReq;
import com.cmc.cmc_server.errors.CustomException;
import com.cmc.cmc_server.errors.ErrorCode;
import com.cmc.cmc_server.infra.ChallengeRepository;
import com.cmc.cmc_server.infra.UserChallengeRepository;
import com.cmc.cmc_server.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;
    private final UserChallengeRepository userChallengeRepository;

    public List<Challenge> getCount(Long id) {
        return challengeRepository.findAllByOwnerIdOrderByCountsDesc(id);
    }

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

    public List<Challenge> getRoom() {
        return challengeRepository.findAll();
    }

    public Challenge getRoom(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_MATCHED));
    }

    public void enterRoom(ChallengeReq challengeReq) {
        User user = userRepository.getById(challengeReq.getUserId());
        List<Challenge> challenge = challengeRepository.findAllById(challengeReq.getRoomId());
        List<UserChallenge> userChallenges = new ArrayList<>();

        for (Challenge challenges : challenge) {
            UserChallenge save = userChallengeRepository.save(
                    UserChallenge.builder().user(user)
                            .challenge(challenges)
                            .build());

            for (UserChallenge userChallenge : userChallenges) {
                userChallenge = save;
            }
        }

    }

}
