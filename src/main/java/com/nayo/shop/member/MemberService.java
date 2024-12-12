package com.nayo.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        Member member = new Member();
        var hash = new BCryptPasswordEncoder().encode(memberDTO.getPassword());
        member.setUsername(memberDTO.getUsername());
        member.setPassword(hash);
        member.setDisplayName(memberDTO.getDisplayName());
        memberRepository.save(member);
    }
}
