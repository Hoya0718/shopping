package com.nayo.shop.security;

import com.nayo.shop.member.CustomUser;
import com.nayo.shop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//DB에 있던 유저 정보 꺼내는 코드
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    //db에서 유저 아이디를 가져와 해당 유저의 아이디 비번 권한을 주세요
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        var result = memberRepository.findByUsername(username);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
        }
        var user = result.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("일반유저")); //나중에 API에서 현재 유저의 권한 출력가능

        var a = new CustomUser(user.getUsername(), user.getPassword(), authorities);
        a.displayName = user.getDisplayName();
        return a;
        //return new User(user.getUsername(), user.getPassword(), authorities); 사용자명 패스워드 권한만 가능 만약 nickname을 추가하고 싶으면??? User()와 비슷한 클래스 생성해야된다.
    }
}
/*
    유저의 로그인 제출 폼 받고 유저 디테일 서비스에서 db에서 찾아서 비교 비번 확인되면 쿠키 쿠키
* */