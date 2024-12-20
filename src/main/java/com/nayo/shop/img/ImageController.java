package com.nayo.shop.img;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ImageController {

    private final ImageService imageService;

    // 썸네일 생성 요청을 처리하는 엔드포인트
    @GetMapping("/generate-thumbnails")
    public String generateThumbnails() {
        try {
            // 썸네일 생성 메서드 호출
            imageService.generateAndSaveThumbnails();
            return "썸네일 생성 완료";
        } catch (Exception e) {
            return "썸네일 생성 실패: " + e.getMessage();
        }
    }
}
