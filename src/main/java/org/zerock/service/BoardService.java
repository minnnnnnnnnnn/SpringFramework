package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDto;
import org.zerock.dto.BoardListPagingDto;
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

    public BoardListPagingDto getList(int page, int size) {

        page = page <= 0 ? 1 : page; // 페이지 번호가 0보다 작으면 무조건 1페이지
        size = (size <= 10 || size > 100) ? 10 : size; // 사이즈가 10보다 작거나 100보다 크면 10

        int skip = (page - 1) * size; // 2페이지라면 (2-1) * 10 이 됨

        List<BoardDto> list = boardMapper.list2(skip, size);

        int total = boardMapper.listCount();

        return new BoardListPagingDto(list, total, page, size);
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

    public void modify(BoardDto boardDto) {

        boardMapper.update(boardDto);
    }

}
