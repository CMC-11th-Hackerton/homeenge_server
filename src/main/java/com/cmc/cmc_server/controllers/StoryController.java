package com.cmc.cmc_server.controllers;

import com.cmc.cmc_server.application.ImageService;
import com.cmc.cmc_server.application.StoryService;
import com.cmc.cmc_server.dto.Story.createStoryReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/story")
@RequiredArgsConstructor
@Api(tags = "스토리 API")
public class StoryController {

    private final StoryService storyService;


    @ApiOperation(value = "스토리 생성", notes = "사용자 스토리를 생성하는 api",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Void> createStory(@ModelAttribute createStoryReq createStoryReq) {
        storyService.create(createStoryReq);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}