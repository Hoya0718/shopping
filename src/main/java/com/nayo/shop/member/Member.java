package com.nayo.shop.member;

import com.nayo.shop.sales.Sales;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Data
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;
    private String password;
    private String displayName;

    @ToString.Exclude //순환참조 방지
    @OneToMany(mappedBy ="member") //member테이블의 참조 컬럼들
    private List<Sales> sales = new ArrayList<>();
}
