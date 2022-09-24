package com.cmc.cmc_server.application;


import com.cmc.cmc_server.domain.Story;
import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.dto.Image.ImageReq;
import com.cmc.cmc_server.dto.Story.createStoryReq;
import com.cmc.cmc_server.infra.StoryRepository;
import com.cmc.cmc_server.infra.UserRepository;
import com.cmc.cmc_server.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final S3Uploader s3Uploader;

    public void create(createStoryReq createStoryReq){
        for(MultipartFile temp : createStoryReq.getImageFiles()){
            Story story = Story.builder()
                    .id(createStoryReq.getId())
                    .imageUrl(s3Uploader.upload(temp, "Story"))
                    .build();
            storyRepository.save(story);
        }
    }
}
