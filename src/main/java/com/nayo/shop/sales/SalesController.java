package com.nayo.shop.sales;

import com.nayo.shop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@ToString
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;
    private final SalesRepository salesRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/updateNum")
    public ResponseEntity<Integer> updateNum(@RequestParam Integer num) {

        Map<String, Integer> map = salesService.updateNum(num);
        return ResponseEntity.ok(map.get("updatedNum"));
    }

    @GetMapping("/sales")
    public String sales(@RequestParam Integer id, Model model) {
        Optional<Sales> result = salesService.findById(id);
        model.addAttribute("sales", result.get());
        return "sales.html";
    }
    @PostMapping("/item/sales")
    public String sales(@ModelAttribute SalesDTO salesDTO, RedirectAttributes redirectAttributes, Authentication auth){
        Integer id = salesService.saveSales(salesDTO, auth);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/sales";
    }

    @GetMapping("/order/all")
    String getOrderAll() {
        List<Sales> result = salesService.customFindAll();
        System.out.println(result);
        var result2 = memberRepository.findById(1);
        System.out.println(result2.get());
        return "sales.html";
    }


}
