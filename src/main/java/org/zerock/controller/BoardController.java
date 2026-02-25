package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.dto.BoardDto;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(Model model) {

        log.info("--------");
        log.info("board list");

        model.addAttribute("list", boardService.getList());
    }

    @GetMapping("/register")
    public void register() {

        log.info("--------");
        log.info("board register");
    }

    @PostMapping("/register")
    public String registerPost(BoardDto dto, RedirectAttributes rttr) {

        log.info("--------");
        log.info("board register post");

        Long bno = boardService.register(dto);
        rttr.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @GetMapping("/read/{bno}")
    public String read(@PathVariable("bno") Long bno, Model model) {

        log.info("--------");
        log.info("board read");

        BoardDto dto = boardService.read(bno);

        model.addAttribute("board", dto);

        return "/board/read";
    }

    @GetMapping("/modify/{bno}")
    public String modifyGET(@PathVariable("bno") Long bno, Model model) {

        log.info("--------");
        log.info("board modify get");

        BoardDto dto = boardService.read(bno);

        model.addAttribute("board", dto);

        return "/board/modify";
    }

    @PostMapping("/modify")
    public String modifyPost(BoardDto boardDto) {

        log.info("--------");
        log.info("board modify post");

        boardService.modify(boardDto);

        return "redirect:/board/read/" + boardDto.getBno();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {

        log.info("--------");
        log.info("board remove post");

        boardService.remove(bno);

        rttr.addFlashAttribute("result", bno);
        return "redirect:/board/list";
    }
}
