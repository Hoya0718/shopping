package com.nayo.shop.sales;

import lombok.Data;

@Data
public class SalesDTO {

    private String itemName;
    private Integer count;
    private Integer price;
    private String seller;
}
