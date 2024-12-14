package com.nayo.shop.item;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity //테이블 생성
@ToString //lombok에서 toString 함수 알아서 만들어 준다.
@Data
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // id컬럼에는 이거 붙이라고 강요함, id값 자동 증가
    private Integer id;
    @Column(nullable = false, unique = true) //제약설정 가능, 컬럼값이 null이면 입력 막아주세요, 값은 중복되면 안돼요, 255자보다 더 큰 몇 만자를 넣고싶어요, 1000자 미만의 글자를 넣고싶어요
    private String title;
    @Column(nullable = false)//db에 바로 반영되지 않는다. 처음부터 설정을 잘하면 좋다. 테이블 삭제하고 다시 만드는게 빠름
    private Integer price;
    private String username;
}

//JPA로 데이터 입출력 3-step
//1. 레포지토리 생성 2. 원하는 클래스에 레포지토리 등록 3. 레포지토리.입출력문법()쓰기