package com.nayo.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
//@Service, @Repository, @Component 3중 택 1
public class ItemService {
    private final ItemRepository itemRepository;

    //Item 불러오기
    public List<Item> findAll() {
        List<Item> result = itemRepository.findAll(); // 테이블의 모든 데이터 가져옴
        return result;
    }

    //Item 등록시 저장
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //Item 상세 정보 불러오기
    public Optional<Item> findById(Integer id){
        Optional<Item> result = itemRepository.findById(id); //Optional : 변수가 비어있을 수도 있고 Item일 수도 있습니다, id가 n인 행 출력
        return result;
    }
}