package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.service.PracticeService;

@Controller
@RequiredArgsConstructor
@ToString
@Log4j2
@RequestMapping("/practice")
public class PracticeController {

    private final PracticeService service;

    // 처음 페이지 접속
    @GetMapping("")
    public String practicePage(Model model) {

        model.addAttribute("menuMap",  service.getMenuMap("ko"));
        return "practice/p";
    }

    @GetMapping("/change")
    public String changeLanguage(@RequestParam(name = "lang") String lang, Model model) {

        model.addAttribute("menuMap", service.getMenuMap(lang));

        // 리렌더링
        return "practice/p";
    }

}
