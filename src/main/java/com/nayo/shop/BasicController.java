package com.nayo.shop;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller // Controller + ResponseBody를 결합
public class BasicController {

    @GetMapping( "/") //URL로 접속 시 return 데이터를 응답
    String hello() {
        return "redirect:index.html";
    }
}
