package com.cmc.cmc_server.controllers;

import com.cmc.cmc_server.application.ImageService;
import com.cmc.cmc_server.dto.Image.ImageReq;
import com.cmc.cmc_server.dto.Image.ImageRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@Api(tags = "이미지 API")
public class ImageController {

    private final ImageService imageService;

    @ApiOperation(value = "이미지 생성", notes = "Form Data 값을 받아와서 이미지를 생성하는 API",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping
    public ImageRes createPost(@ModelAttribute ImageReq imageReq){
        return imageService.createPost(imageReq);
    }

}
