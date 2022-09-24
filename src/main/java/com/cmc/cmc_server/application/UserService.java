package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.Daily;
import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.domain.UserChallenge;
import com.cmc.cmc_server.dto.Daily.DailyRes;
import com.cmc.cmc_server.dto.User.UserInfoRes;
import com.cmc.cmc_server.errors.CustomException;
import com.cmc.cmc_server.errors.ErrorCode;
import com.cmc.cmc_server.errors.ErrorResponse;
import com.cmc.cmc_server.infra.DailyRepository;
import com.cmc.cmc_server.infra.UserChallengeRepository;
import com.cmc.cmc_server.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public UserInfoRes getUserInfo(Long userId) throws Exception {

        // 유저 email, nickname, points
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 챌린지 방 id, 미션 title
        UserChallenge challenge = userChallengeRepository.findByUser_Id(userId);

        return new UserInfoRes(user.getEmail(), user.getNickname(), user.getPoints(),
                challenge.getId(), challenge.getChallenge().getMission().getTitle());
    }


}
