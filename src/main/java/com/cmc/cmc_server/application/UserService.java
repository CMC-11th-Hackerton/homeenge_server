package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.domain.UserChallenge;
import com.cmc.cmc_server.dto.Challenge.ChallengeRes;
import com.cmc.cmc_server.dto.User.UserInfoRes;
import com.cmc.cmc_server.errors.CustomException;
import com.cmc.cmc_server.infra.UserChallengeRepository;
import com.cmc.cmc_server.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.cmc.cmc_server.errors.ErrorCode.USER_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserChallengeRepository userChallengeRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    // 유저 마이페이지 조회
    public UserInfoRes getUserInfo(Long userId) throws Exception {

        // 유저 email, nickname, points
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 챌린지 방 id, 미션 title
        LocalDateTime standard = LocalDateTime.now().minusHours(24);
        List<UserChallenge> challengeList = userChallengeRepository.findChallenges(userId, standard);

        List<ChallengeRes> challengeResList = new ArrayList<>();
        for (UserChallenge userChallenge : challengeList) {
            challengeResList.add(new ChallengeRes(userChallenge.getId(), userChallenge.getChallenge().getMission().getTitle()));
        }

        return new UserInfoRes(user.getEmail(), user.getNickname(), user.getPoints(),
                challengeResList);
    }


}
