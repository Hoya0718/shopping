package com.nayo.shop.comment;

import com.nayo.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @PreAuthorize("authentication != null") //로그인 검사
    public void postComment(CommentDTO commentDTO, Authentication auth) {

        Comment comment = new Comment();
        // comment.setUsername(auth.getName()); 유저의 아이디만 가져오고 싶을 때 사용, 유저의 모든 정보를 가져오고 싶다면 아래 참고
        CustomUser user = (CustomUser)auth.getPrincipal();
        comment.setUsername(user.getUsername());
        comment.setItemId( commentDTO.getItemId());
        comment.setComment(commentDTO.getComment());

        commentRepository.save(comment);
    }
}
