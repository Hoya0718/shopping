package com.nayo.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Integer> {//<Entity명, id컬럼타입>
    Page<Item> findPageBy(Pageable page); //pagination하는 방법

    List<Item> findAllByTitle(String title);
    //Slice<Item> findSliceBy(Pageable page); //전체 행 갯수 세는 SQL 코드 실행안함(전체 페이지 갯수 필요없을 때 사용)

    List<Item> findByTitleContaining(String title);

    /* JPA에서 쿼리문 작성 법 (파라미터문법 가능)
    @Query(value = "select * from item here id =?1", nativeQuery = true)
    Item rawQuery1(3L); -> ID가 3인 아이템 가져와

    @Query(value = "select * from item where match(title) against(?1)" nativaQuery = true)
    List<Item> rawQuery2(String text);
    */
    @Query("SELECT i from Item i Left Join fetch i.images where i.id = :id")
    Optional<Item> findByIdWithImages(Integer id);

    @Query("SELECT DISTINCT i FROM Item i LEFT JOIN FETCH i.images")
    Page<Item> findAllWithImages(Pageable pageable);
}
