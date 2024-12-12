package com.nayo.shop;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class ItemDTO {
    private Integer id;
    private String title;
    private Integer price;
}
