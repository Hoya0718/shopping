package com.nayo.shop.sales;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String member_id; //구매자
    private String seller; //판매자
    @CreationTimestamp//자동으로
    private LocalDateTime created;
}
