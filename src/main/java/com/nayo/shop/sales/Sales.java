package com.nayo.shop.sales;

import com.nayo.shop.member.Member;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
public class Sales {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //상품 번호
    private String itemName; //상품명
    private Integer price; //가격
    private Integer count; //구매할 개수
    @ManyToOne(fetch = FetchType.EAGER)// Member테이블과 Join는 보이 단점: 참조 테이블 데이터가 많으면 성능 떨어진다. 해결법 :JOIN문법, 단점2: 모든 컬럼 가져옴 eager : 데이터 미리 호출 lazy : 호출 시
    @JoinColumn(name= "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))//외래키 이름작명, 테스트 중이라 제약조건 무시
    private Member member;
    private String seller; //판매자
    @CreationTimestamp//자동으로
    private LocalDateTime created;
}
