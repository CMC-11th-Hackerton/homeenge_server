package com.cmc.cmc_server.application;


import com.cmc.cmc_server.domain.*;
import com.cmc.cmc_server.dto.Image.ImageReq;
import com.cmc.cmc_server.dto.Image.ImageRes;
import com.cmc.cmc_server.dto.Story.*;
import com.cmc.cmc_server.errors.CustomException;
import com.cmc.cmc_server.errors.ErrorCode;
import com.cmc.cmc_server.infra.*;
import com.cmc.cmc_server.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final S3Uploader s3Uploader;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final UserChallengeRepository userChallengeRepository;
    private final ChallengeRepository challengeRepository;

    public void createPost(ImageReq imageReq) {
        String s = uploadPostImages(imageReq);
    }

    /**
     * 이미지 파일 S3 저장 + PostImage 생성
     */
    private String uploadPostImages(ImageReq imageReq) {
        Challenge challenge = challengeRepository.findById(imageReq.getChallengeId()).orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND));

        String url = s3Uploader.upload(imageReq.getImageFiles(), "post");
        Story story = createStory(url, imageReq.getId(), challenge);
        return story.getImageUrl();

    }

    /**
     * Story 생성 메서드
     */
    private Story createStory(String url, long id, Challenge challenge) {
        System.out.println("url = " + url);
        return storyRepository.save(Story.builder()
                .userId(id)
                .challenge(challenge)
                .imageUrl(url)
                .build());
    }

    // 스토리 신고
    public void reportStory(ReportStoryReq reportStoryReq) {
        long storyId = reportStoryReq.getStoryId();
        Story story = storyRepository.findById(storyId).orElseThrow(() -> new CustomException(ErrorCode.STORY_NOT_FOUND));

        story.setReport(story.getReport()+1);
        storyRepository.save(story);

        this.checkStory(story);
    }

    // 스토리 신고 과다 체크
    private void checkStory(Story story) {
        if(story.getReport() >= 3) {
            storyRepository.delete(story);
        }
    }

    // 스토리 조회
    public GetStoryRes getStory(GetStoryReq getStoryReq) {
        User user = userRepository.findById(getStoryReq.getUserId()).orElseThrow(() -> new CustomException(ErrorCode.STORY_NOT_FOUND));
        List<Story> story = storyRepository.findByUserIdAndChallenge_Id(getStoryReq.getUserId(), getStoryReq.getChallengeId());

        List<String> urls = new ArrayList<>();
        for (Story s : story) {
            urls.add(s.getImageUrl());
        }

        return new GetStoryRes(user.getNickname(), urls);
    }

    public ChallengeDetailRes getChallengeDetail(Long challengeId){
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND));
        Long time = ChronoUnit.SECONDS.between(LocalDateTime.now(), challenge.getFinishTime());
        Long hours = time / (60 * 60);
        Long minutes = (time / 60) % 60;
        Long seconds = time % 60;

        String lastTime = hours.toString() + ":" + minutes.toString() + ":" + seconds.toString();

        List<UserChallenge> userChallengeList = userChallengeRepository.findAllByChallenge(challenge);
        List<UserStoryRes> userStoryResList = new ArrayList<>();
        for(UserChallenge temp: userChallengeList){
            UserStoryRes userStoryRes = new UserStoryRes(
                    temp.getUser().getId(),
                    temp.isIsSuccess(),
                    temp.getUser().getImageUrl()
            );
            userStoryResList.add(userStoryRes);
        }

        return ChallengeDetailRes.builder()
                .title(challenge.getTitle())
                .counts(challenge.getCounts())
                .likes(challenge.getLikes())
                .lastTime(lastTime)
                .userStoryResList(userStoryResList)
                .build();
    }
}
