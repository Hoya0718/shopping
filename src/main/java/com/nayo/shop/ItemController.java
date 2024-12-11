package com.nayo.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("실행 전");
        List<Item> result = itemService.findAll();
        System.out.println("실행 후"+result.size());
        model.addAttribute("items", result);
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
    public String item(@ModelAttribute Item item) { //<input>데이터들을 바로 object로 변환
        itemService.saveItem(item);
        return "redirect:/list"; //특정페이지로 돌아가게 만들 수 있음
    }

    //REST API 서버 에러 처리 1.try-catch문
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) { //user가 입력한 파라미터의 값을 알 수 있음
        Optional<Item> result = itemService.findById(id);

        if (result.isPresent()) { //Optional은 if문으로 꼭 해줘야된다. result에 결과가 있을 때
            model.addAttribute("id", result.get().getId());
            model.addAttribute("title", result.get().getTitle());
            model.addAttribute("price", result.get().getPrice());
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
            return "modification.html";
        }
        else{
            return "redirect:/list";
        }
    }

    @PutMapping("/modification/{id}")
    public String modification(@ModelAttribute Item item) {
        itemService.saveItem(item);
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
}
