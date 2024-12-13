package com.nayo.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    String postComment(@ModelAttribute CommentDTO commentDTO, Authentication auth) {
        System.out.println(commentDTO+"이거 참조");
        commentService.postComment(commentDTO, auth);
        return "/detial";
    }
}
