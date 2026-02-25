package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.BoardDto;

import java.util.List;

public interface BoardMapper {

    int insert(BoardDto dto);

    BoardDto selectOne(Long bno);

    int remove(Long bno);

    int update(BoardDto dto);

    List<BoardDto> list();

    List<BoardDto> list2(@Param("skip")int skip, @Param("count") int count);

    int listCount();

}
