package org.zerock.mapper;

import org.zerock.dto.BoardDto;

import java.util.List;

public interface BoardMapper {

    int insert(BoardDto dto);

    BoardDto selectOne(Long bno);

    int remove(Long bno);

    int update(BoardDto dto);

    List<BoardDto> list();

}
