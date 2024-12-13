package com.nayo.shop.item;

import com.nayo.shop.comment.Comment;
import com.nayo.shop.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
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

    //Item 등록시 저장 또는 수정
    public void saveItem(ItemDTO itemDTO, Authentication auth){
        Item item = new Item();
        if(itemDTO.getId() != null){
            item.setId(itemDTO.getId());
        }

        item.setTitle(itemDTO.getTitle());
        item.setPrice(itemDTO.getPrice());
        item.setUsername(auth.getName());
        System.out.println("서비스"+ item);
        itemRepository.save(item);
    }

    //Item 상세 정보 불러오기
    public Optional<Item> findById(Integer id){
        Optional<Item> result = itemRepository.findById(id); //Optional : 변수가 비어있을 수도 있고 Item일 수도 있습니다, id가 n인 행 출력
        return result;
    }

    public void deleteById(Integer id){
        itemRepository.deleteById(id);
    }

    // 페이징 처리된 아이템을 반환
    public Page<Item> findPagedItems(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // page는 0부터 시작
        return itemRepository.findAll(pageable);
    }

}
