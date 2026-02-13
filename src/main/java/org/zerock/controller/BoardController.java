package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/list")
    public void list() {

        log.info("--------");
        log.info("board list");
    }

    @GetMapping("/register")
    public void register() {

        log.info("--------");
        log.info("board register");
    }

    @PostMapping("/register")
    public String registerPost() {

        log.info("--------");
        log.info("board register post");

        return "redirect:/board/list";
    }
}
