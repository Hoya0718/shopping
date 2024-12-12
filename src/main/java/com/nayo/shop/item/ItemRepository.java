package com.nayo.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {//<Entity명, id컬럼타입>
    Page<Item> findPageBy(Pageable page); //pagination하는 방법
    //Slice<Item> findSliceBy(Pageable page); //전체 행 갯수 세는 SQL 코드 실행안함(전체 페이지 갯수 필요없을 때 사용)
}
