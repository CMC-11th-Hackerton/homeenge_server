package com.cmc.cmc_server.application;


import com.cmc.cmc_server.domain.PostImage;
import com.cmc.cmc_server.domain.Report;
import com.cmc.cmc_server.domain.Story;
import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.dto.Image.ImageReq;
import com.cmc.cmc_server.dto.Image.ImageRes;
import com.cmc.cmc_server.dto.Story.ReportStoryReq;
import com.cmc.cmc_server.dto.Story.createStoryReq;
import com.cmc.cmc_server.infra.ReportRepository;
import com.cmc.cmc_server.infra.StoryRepository;
import com.cmc.cmc_server.infra.UserRepository;
import com.cmc.cmc_server.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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

    public ImageRes createPost(ImageReq imageReq) {
        List<String> postImages = uploadPostImages(imageReq);
        return ImageRes.builder().imageUrl(postImages).build();
    }

    /**
     * 이미지 파일 S3 저장 + PostImage 생성
     */
    private List<String> uploadPostImages(ImageReq imageReq) {
        return imageReq.getImageFiles().stream()
                .map(image -> s3Uploader.upload(image, "post"))
                .map(url -> createStory(url, imageReq.getId()))
                .map(postImage -> postImage.getImageUrl())
                .collect(Collectors.toList());
    }

    /**
     * Story 생성 메서드
     */
    private Story createStory(String url, long id) {
        System.out.println("url = " + url);
        return storyRepository.save(Story.builder()
                .userId(id)
                .imageUrl(url)
                .build());
    }

    // 스토리 신고
    public void reportStory(ReportStoryReq reportStoryReq) {
        long userId = reportStoryReq.getUserId();
        long storyId = reportStoryReq.getStoryId();
        reportRepository.save(Report.builder().userId(userId).storyId(storyId).build());
    }
}
