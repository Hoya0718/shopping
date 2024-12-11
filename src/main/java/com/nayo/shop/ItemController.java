package com.nayo.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    public String list(Model model){

        List<Item> result = itemRepository.findAll(); // 테이블의 모든 데이터 가져옴
        model.addAttribute("items", result);
        var a = new Item();
        System.out.println(a.toString()); //toString 생략 가능
        return "list.html";
    }
}
