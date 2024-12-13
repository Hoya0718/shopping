package com.nayo.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    String postComment(@ModelAttribute CommentDTO commentDTO, Authentication auth, Model model) {
        commentService.postComment(commentDTO, auth);
        var id = commentDTO.getItemId();
        model.addAttribute("id", id);
        return "redirect:detail/" + id;
    }
}
