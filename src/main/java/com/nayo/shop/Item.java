package com.nayo.shop;

import jakarta.persistence.*;

@Entity //테이블 생성
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // id컬럼에는 이거 붙이라고 강요함, id값 자동 증가
    public Integer id;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT", length = 1000) //제약설정 가능, 컬럼값이 null이면 입력 막아주세요, 값은 중복되면 안돼요, 255자보다 더 큰 몇 만자를 넣고싶어요, 1000자 미만의 글자를 넣고싶어요
    public String title;
    @Column()//db에 바로 반영되지 않는다. 처음부터 설정을 잘하면 좋다. 테이블 삭제하고 다시 만드는게 빠름
    public Integer price;
}
