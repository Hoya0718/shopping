package com.nayo.shop.announcement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementRepository announcementRepository;

    @GetMapping("/announcement")
    public String announcement(Model model) {
        List<Announcement> announcements = announcementRepository.findAll();
        model.addAttribute("announcements", announcements);
        System.out.println("공지사항" + announcementRepository.findAll());
        return "announcement.html";
    }
}
