package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.Challenge;
import com.cmc.cmc_server.domain.Mission;
import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.domain.UserChallenge;
import com.cmc.cmc_server.dto.Challenge.*;
import com.cmc.cmc_server.dto.User.UserResultData;
import com.cmc.cmc_server.errors.CustomException;
import com.cmc.cmc_server.errors.ErrorCode;
import com.cmc.cmc_server.infra.ChallengeRepository;
import com.cmc.cmc_server.infra.MissionRepository;
import com.cmc.cmc_server.infra.UserChallengeRepository;
import com.cmc.cmc_server.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.cmc.cmc_server.errors.ErrorCode.CHALLENGE_NOT_FOUND;

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

    public void createRoom(RoomReq roomReq) {
        User user = userRepository.findById(roomReq.getLeaderId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Mission mission = missionRepository.findById(roomReq.getMissionId())
                .orElseThrow(() -> new CustomException(CHALLENGE_NOT_FOUND));

        String input = "20220925" + roomReq.getEndTime();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime ldt = LocalDateTime.parse(input.substring(0, 14), f);

        Challenge challenge = Challenge.builder()
                .owner(user)
                .title(roomReq.getTitle())
                .counts(roomReq.getCounts())
                .mission(mission)
                .finishTime(ldt)
                .content(roomReq.getContent())
                .build();

        userChallengeRepository.save(UserChallenge.builder()
                .challenge(challenge)
                .user(user)
                .build());

        challengeRepository.save(challenge);
    }

    public List<ChallRes> getRoomByStep(Integer step) {
        List<Challenge> allByStep = challengeRepository.findAllByStep(step);
        List<ChallRes> challRes = new ArrayList<>();
        for (Challenge challenge : allByStep) {
            challRes.add(ChallRes.builder()
                    .id(challenge.getId())
                    .missionName(challenge.getMission().getTitle())
                    .challengeName(challenge.getTitle())
                    .content(challenge.getContent())
                    .counts(challenge.getCounts())
                    .currCounts(challenge.getCurrCounts())
                    .endTime(challenge.getFinishTime())
                    .step(challenge.getStep())
                    .imageUrl(challenge.getMission().getImageUrl())
                    .build());
        }
        return challRes;
    }

    public RoomRes getRoom(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_MATCHED));
        List<UserChallenge> Challenge = userChallengeRepository.findAllByChallenge(challenge);
        List<UserResultData> userResultData = new ArrayList<>();
        for (UserChallenge userChallenge : Challenge) {
            userResultData.add(UserResultData.builder()
                    .id(userChallenge.getUser().getId())
                    .nickname(userChallenge.getUser().getNickname())
                    .imageUrl(userChallenge.getUser().getImageUrl())
                    .build());
        }

        return RoomRes.builder()
                .title(challenge.getTitle())
                .missionName(challenge.getMission().getTitle())
                .counts(challenge.getCounts())
                .currCounts(challenge.getCurrCounts())
                .userResultData(userResultData)
                .content(challenge.getContent())
                .endTime(challenge.getFinishTime())
                .build();

    }

    public MyChall getMyAllChallenge(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<CurrChall> currChalls = new ArrayList<>();
        List<MadeChall> madeChalls = new ArrayList<>();

        List<Challenge> madeChall = challengeRepository.findAllByOwner(user);

        for (Challenge challenge : madeChall) {
            madeChalls.add(MadeChall.builder()
                    .id(challenge.getId())
                    .imageUrl(challenge.getMission().getImageUrl())
                    .missionName(challenge.getMission().getTitle())
                    .finished(challenge.isFinished())
                    .build());
        }
        List<UserChallenge> currChall = userChallengeRepository.findAllByUser(user);
        for (UserChallenge challenge : currChall) {
            currChalls.add(CurrChall.builder()
                    .id(challenge.getId())
                    .imageUrl(challenge.getChallenge().getMission().getImageUrl())
                    .missionName(challenge.getChallenge().getMission().getTitle())
                    .finished(challenge.getChallenge().isFinished())
                    .build());
        }
        return MyChall.builder().madeChalls(madeChalls).currChalls(currChalls).build();


    }

    public MyChall getMyCurrChallenge(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<CurrChall> currChalls = new ArrayList<>();
        List<MadeChall> madeChalls = new ArrayList<>();

        List<Challenge> madeChall = challengeRepository.findAllByOwnerAndFinishedIsFalse(user);

        for (Challenge challenge : madeChall) {
            madeChalls.add(MadeChall.builder()
                    .id(challenge.getId())
                    .imageUrl(challenge.getMission().getImageUrl())
                    .missionName(challenge.getMission().getTitle())
                    .finished(challenge.isFinished())
                    .build());
        }

        List<UserChallenge> currChall = userChallengeRepository.findAllByUser(user);
        for (UserChallenge challenge : currChall) {
            if (challenge.getChallenge().isFinished() == false) {
                currChalls.add(CurrChall.builder()
                        .id(challenge.getId())
                        .imageUrl(challenge.getChallenge().getMission().getImageUrl())
                        .missionName(challenge.getChallenge().getMission().getTitle())
                        .finished(challenge.getChallenge().isFinished())
                        .build());
            }
        }
        return MyChall.builder().madeChalls(madeChalls).currChalls(currChalls).build();
    }

    public MyChall getMyEndChallenge(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<CurrChall> currChalls = new ArrayList<>();
        List<MadeChall> madeChalls = new ArrayList<>();

        List<Challenge> madeChall = challengeRepository.findAllByOwnerAndFinishedIsTrue(user);

        for (Challenge challenge : madeChall) {
            madeChalls.add(MadeChall.builder()
                    .id(challenge.getId())
                    .imageUrl(challenge.getMission().getImageUrl())
                    .missionName(challenge.getMission().getTitle())
                    .finished(challenge.isFinished())
                    .build());
        }

        List<UserChallenge> currChall = userChallengeRepository.findAllByUser(user);
        for (UserChallenge challenge : currChall) {
            if (challenge.getChallenge().isFinished() == true) {
                currChalls.add(CurrChall.builder()
                        .id(challenge.getId())
                        .imageUrl(challenge.getChallenge().getMission().getImageUrl())
                        .missionName(challenge.getChallenge().getMission().getTitle())
                        .finished(challenge.getChallenge().isFinished())
                        .build());
            }
        }
        return MyChall.builder().madeChalls(madeChalls).currChalls(currChalls).build();

    }

    public void enterRoom(ChallengeReq challengeReq) {
        User user = userRepository.getById(challengeReq.getUserId());
        Challenge challenge = challengeRepository.getById(challengeReq.getRoomId());
        challenge.addCurrCounts();

        userChallengeRepository.save(
                UserChallenge.builder().challenge(challenge).user(user)
                        .build());
    }

    // 지목할 사람 리스트
    public List<GetNominationRes> getNomination(Long challengeId) {
        //challenge 통해서 nomination false인 사람 찾아서 반환하기
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new CustomException(CHALLENGE_NOT_FOUND));
        List<UserChallenge> challengeList = userChallengeRepository.findByChallenge(challenge);

        return challengeList.stream()
                .map(c -> new GetNominationRes(c.getUser().getId(), c.getUser().getNickname()))
                .collect(Collectors.toList());
    }

    // 지목하기
    public void nominationUser(NominateReq nominateReq) {
        userChallengeRepository.modifynom(nominateReq.getChallengeId(), nominateReq.getUserId());

    }


}
