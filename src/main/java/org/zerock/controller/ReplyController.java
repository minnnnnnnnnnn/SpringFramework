package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.dto.ReplyDto;
import org.zerock.dto.ReplyListPagingDto;
import org.zerock.service.ReplyService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("")
    public ResponseEntity<Map<String, Long>> add(ReplyDto replyDto) { // json 타입 변환을 위해 map 타입 처리

        log.info(replyDto);

        replyService.add(replyDto);

        return ResponseEntity.ok(Map.of("result", replyDto.getRno()));
    }

    @GetMapping("/{bno}/list")
    public ResponseEntity<ReplyListPagingDto> listOfBoard(
            @PathVariable("bno") Long bno,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        log.info("bno: " + bno);
        log.info("page: " + page);
        log.info("size: " + size);

        return ResponseEntity.ok(replyService.listOfBoard(bno, page, size));
    }

    @GetMapping("/{rno}")
    public ResponseEntity<ReplyDto> read(@PathVariable("rno") Long rno) {
        log.info("rno: " + rno);

        return ResponseEntity.ok(replyService.getOne(rno));
    }

}
