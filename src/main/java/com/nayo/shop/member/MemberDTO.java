package com.nayo.shop.member;

import lombok.Data;

@Data
public class MemberDTO {
    private Integer id;
    private String username;
    private String password;
    private String displayName;
}
