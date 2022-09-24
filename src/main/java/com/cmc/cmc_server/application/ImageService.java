package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.PostImage;
import com.cmc.cmc_server.dto.Image.ImageReq;
import com.cmc.cmc_server.dto.Image.ImageRes;
import com.cmc.cmc_server.infra.PostImageRepository;
import com.cmc.cmc_server.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final S3Uploader s3Uploader;
    private final PostImageRepository postImageRepository;

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
                .map(url -> createPostImage(url))
                .map(postImage -> postImage.getImageUrl())
                .collect(Collectors.toList());
    }

    /**
     * PostImage 생성 메서드
     */
    private PostImage createPostImage(String url) {
        System.out.println("url = " + url);
        return postImageRepository.save(PostImage.builder()
                .imageUrl(url)
                .build());
    }
}
