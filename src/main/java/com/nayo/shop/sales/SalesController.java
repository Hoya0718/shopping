package com.nayo.shop.sales;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@ToString
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;

    @PostMapping("/updateNum")
    public ResponseEntity<Integer> updateNum(@RequestParam Integer num) {

        Map<String, Integer> map = salesService.updateNum(num);
        System.out.println(map+"과연 뭐가 나올까 ㅋㅋ");
        return ResponseEntity.ok(map.get("updatedNum"));
    }

    @GetMapping("/sales/{id}")
    public String sales(@PathVariable Integer id, Model model) {

        Optional<Sales> result = salesService.sales(id);
        if(result.isPresent()) {
            model.addAttribute("sales", result.get());
            return "sales.html";
        }
        else {
            return "redirect:/detail/" + id ;
        }
    }
}
