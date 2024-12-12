package com.nayo.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    String join(Authentication auth) {
        if(auth != null) {
            return "redirect:/main";
        }
        return "register.html";
    }

    @PostMapping("/member")
    String addMember(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "redirect:/main";
    }

    @GetMapping("/login")
    String login(Authentication auth){
        if(auth != null){
            return "redirect:/main";
        }
            return "/login.html";
    }

    @GetMapping("/my-page")
    String mypage(Authentication auth) { //유저 정보 볼수있는 오브젝트
        //System.out.println(auth);
        //System.out.println(auth.getName());
        //System.out.println(auth.isAuthenticated()); //auth가 Authentication의 시큐리티 객체인지 확인
        //System.out.println(auth.getAuthorities()); 권한 확인
        //System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저"))); 일반유저인지 확인
        return "mypage.html";
    }
}
