package com.nayo.shop.comment;

import com.nayo.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void postComment(CommentDTO commentDTO, Authentication auth) {

        Comment comment = new Comment();
        CustomUser user = (CustomUser) auth.getPrincipal();
        System.out.println(user);
        // comment.setUsername(auth.getName()); 유저의 아이디만 가져오고 싶을 때 사용, 유저의 모든 정보를 가져오고 싶다면 아래 참고
        comment.setUsername(user.getUsername());
        comment.setItemId( commentDTO.getItemId());
        comment.setComment(commentDTO.getComment());

        commentRepository.save(comment);
        System.out.println("저장 데이터"+comment);
    }

    public List<Comment> findAllByItemId(Integer id) {
        List<Comment> list = commentRepository.findAllByItemId(id);
        return list;
    }
}
