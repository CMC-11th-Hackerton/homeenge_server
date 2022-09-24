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
    private final DailyRepository dailyRepository;

    public User create(User user) {
        return userRepository.save(user);
    }
/*
    public UserInfoRes getUserInfo(Long userId) throws Exception {

        // 유저 email, nickname
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 챌린지 방 id, 미션 title
        // 챌린지 방 id -> userId로 userChallenge에서 받아오기
        // userChallenge의 challenge의 title 가져오기
        UserChallenge challenge = userChallengeRepository.findByUser_Id(userId);

        // 데일리 리스트 정보 (한 달)
        List<Daily> dailyList = dailyRepository.findByUser(user);

        List<DailyRes> dailyResList = new ArrayList<>();
        for (Daily daily : dailyList) {
            dailyResList.add(new DailyRes(daily.getCreateAt(), daily.isSuccess()));
        }

        return new UserInfoRes(user.getEmail(), user.getNickname(),
                challenge.getId(), challenge.getChallenge().getMission().getTitle(),
                dailyResList);
    }
    */

}
