package com.nayo.shop.img;

import com.nayo.shop.item.Item;
import com.nayo.shop.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ItemRepository itemRepository;

    @Value("${thumbnail.save.path}")
    private String thumbnailSavePath;  // application.properties에서 경로를 읽어옴

    public void generateAndSaveThumbnails() throws IOException {
        List<String> imageUrls = getURL();  // 이미지 URL 목록을 가져옴
        System.out.println(imageUrls);

        // URL 목록을 순차적으로 처리
        for (String imageUrl : imageUrls) {
            try {
                // 이미지 다운로드 및 썸네일 생성
                downloadAndCreateThumbnail(imageUrl);
            } catch (IOException e) {
                System.err.println("썸네일 생성 실패: " + imageUrl);
                e.printStackTrace();
            }
        }
    }

    // DB에서 이미지 URL을 가져오는 메서드 (예시)
    public List<String> getURL() {
        List<String> list = new ArrayList<>();
        List<Item> items = itemRepository.findAll();  // 예시로 모든 아이템을 가져온다고 가정

        for (Item item : items) {
            if (item.getImages() != null && !item.getImages().isEmpty()) {
                list.add(item.getImages().get(0).getImage_url());  // Image 엔티티에서 URL을 얻는다고 가정
            }
        }
        return list;
    }

    // 이미지 URL을 다운로드하고 썸네일을 생성하여 로컬에 저장하는 메서드
    private void downloadAndCreateThumbnail(String imageUrl) throws IOException {
        InputStream inputStream = new URL(imageUrl).openStream();

        // 프로퍼티 파일에서 읽은 경로 사용
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        Path outputPath = Paths.get(thumbnailSavePath, fileName);

        // 디렉토리가 없다면 생성
        Files.createDirectories(outputPath.getParent());  // static/img 디렉토리가 없으면 생성

        // 썸네일 생성 및 저장 (크기는 100x100으로 설정)
        Thumbnails.of(inputStream)
                .size(100, 100)  // 썸네일 크기 설정
                .toFile(outputPath.toFile());

        // 스트림 닫기
        inputStream.close();

        System.out.println("썸네일이 저장되었습니다: " + outputPath);
    }
}
