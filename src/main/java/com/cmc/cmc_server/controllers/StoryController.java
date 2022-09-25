package com.cmc.cmc_server.controllers;

import com.cmc.cmc_server.application.StoryService;
import com.cmc.cmc_server.dto.Image.ImageReq;
import com.cmc.cmc_server.dto.Story.GetStoryReq;
import com.cmc.cmc_server.dto.Story.ReportStoryReq;
import com.cmc.cmc_server.dto.Story.ChallengeDetailRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> createStory(@ModelAttribute ImageReq imageReq) {
        storyService.createPost(imageReq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 스토리 신고 API*
     * @param reportStoryReq(userId, storyId)
     */
    @PostMapping("/report")
    @ApiOperation(value = "스토리 신고", notes = "스토리를 신고한다.")
    public ResponseEntity<?> reportStory(@RequestBody ReportStoryReq reportStoryReq) {
        storyService.reportStory(reportStoryReq);
        return ResponseEntity.ok().build();
    }

    /**
     * 유저 스토리 조회 API*
     * @param getStoryReq
     * @return getStoryRes
     */
    @PostMapping("/search")
    @ApiOperation(value = "스토리 조회", notes = "스토리를 조회한다.")
    public ResponseEntity<?> getStory(@RequestBody GetStoryReq getStoryReq) {
        try {
            return ResponseEntity.ok().body(storyService.getStory(getStoryReq));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "스토리 목록", notes = "사용자 스토리 목록을 조회하는 api")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detail")
    public ChallengeDetailRes getUserChallengeDetail(@RequestParam("challengeId") Long challengeId) {
        return storyService.getChallengeDetail(challengeId);
    }

}
