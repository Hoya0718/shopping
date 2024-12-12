package com.nayo.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//스프링 시큐리티 설정

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean //다른 사람이 만든 클래스를 DI로 사용 빈으로 등록
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //FilterChain으로 어떤 페이지를 로그인할지 설정 가능
    //유저의 요청과 서버의 응답 사이에 자동으로 실행해주고 싶은 코드 담는 곳
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()); //다른 사이트에서 form action="내사이트.com/write 해서"api 요청 가능 -> csrf공격이라고한다. 만약 실행시 내 사이트의 form의 value에 서버에서 랜덤한 문자 만든다. 서버랑 비교 후 확인
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
        );//특정 페이지 로그인검사 유무 설정 가능
        return http.build();
    }
}

//jwt의 경우 입장권을 fetch()의 헤더에 같이 넣으면 해결가능