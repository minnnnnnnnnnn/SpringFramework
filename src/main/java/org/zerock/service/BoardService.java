package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDto;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;

    public List<BoardDto> getList() {

        return boardMapper.list();
    }

    public Long register(BoardDto dto) {

        int insertCount = boardMapper.insert(dto);

        log.info("insertCount: " + insertCount);

        return dto.getBno();
    }

    public BoardDto read(Long bno) {

        BoardDto boardDto = boardMapper.selectOne(bno);

        return boardDto;
    }

    public void remove(Long bno) {

        boardMapper.remove(bno);
    }

}
