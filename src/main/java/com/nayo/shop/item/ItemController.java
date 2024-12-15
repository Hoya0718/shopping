package com.nayo.shop.item;

import com.nayo.shop.comment.Comment;
import com.nayo.shop.comment.CommentService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CommentService commentService;

    @GetMapping("/list")
    public String list(Model model) {
        Page<Item> result = itemService.findPagedItems(0, 6);
        model.addAttribute("items", result.getContent());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("currentPage", 1);
        return "list.html";
    }

    @GetMapping("/list/page/{abc}")
    String getListPage(Model model, @PathVariable Integer abc) {
        int page = abc > 0 ? abc-1:0;
        int size = 6;
        Page<Item> result = itemService.findPagedItems(page, size);
        model.addAttribute("items", result.getContent());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("currentPage", page+1);
        return "list.html";
    }

    @GetMapping("/addition")
    public String addition() {
        return "addition.html";
    }

    @PostMapping("/item")
    /* 파라미터가 많다면?
    public String item(@RequestParam Map formData){}
    */
    /* 데이터가 얼마 없다면?
    public String item(@RequestParam String title) {} //보낸 인자를 내가 정한 타입으로 선택 가능
    */
    public String item(@ModelAttribute ItemDTO itemDTO, Authentication auth) { //<input>데이터들을 바로 object로 변환
        itemService.saveItem(itemDTO, auth);
        return "redirect:/list"; //특정페이지로 돌아가게 만들 수 있음
    }

    //REST API 서버 에러 처리 1.try-catch문
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) { //user가 입력한 파라미터의 값을 알 수 있음
        Optional<Item> result = itemService.findById(id);
        List<Comment> comment = commentService.findAllByItemId(id);

        if (result.isPresent()) { //Optional은 if문으로 꼭 해줘야된다. result에 결과가 있을 때
            model.addAttribute("id", id);
            model.addAttribute("title", result.get().getTitle());
            model.addAttribute("price", result.get().getPrice());
            model.addAttribute("username" , result.get().getUsername());
            model.addAttribute("comments", comment);
            model.addAttribute("defaultNum",1);

            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

    @GetMapping("/modification/{id}")
    public String modification(@PathVariable Integer id, Model model) {
        Optional<Item> result = itemService.findById(id);
        if (result.isPresent()) {
            model.addAttribute("id", result.get().getId());
            model.addAttribute("title", result.get().getTitle());
            model.addAttribute("price", result.get().getPrice());
            model.addAttribute("username" , result.get().getUsername());
            return "modification.html";
        }
        else{
            return "redirect:/list";
        }
    }

    @PostMapping("/modification/{id}")
    public String modification(@ModelAttribute ItemDTO itemDTO, Authentication auth) {
        itemService.saveItem(itemDTO, auth);
        return "redirect:/list";
    }

    /* 그냥 id값이 있다면 그건 update문으로 변한다.
    @PostMapping("/modification/{id}")
    public String modification(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return "redirect:/list";
    }
     */

    @DeleteMapping("/detail/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        itemService.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/main")
    public String main(){
        return "main.html";
    }

    @GetMapping("/search")
    public String findByTitleContaining(@RequestParam String title, Model model){

        List<Item> list = itemService.findByTitleContaining(title);
        model.addAttribute("items", list);
        return "/list.html";
    }
}