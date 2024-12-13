package com.nayo.shop.announcement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@ToString
//jpa에서 index 생성하는 법
//@Table(indexes = @Index(columnList = "title", name="작명")) title컬럼에 index 생성
//multi-column index도 가능
public class Announcement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date date;
}
