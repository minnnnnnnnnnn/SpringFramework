package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ReplyDto;

import java.util.List;

public interface ReplyMapper {

    int insert(ReplyDto replyDto);

    ReplyDto read(@Param("rno") Long rno);

    int delete(@Param("rno") Long rno);

    int update(ReplyDto replyDto);

    List<ReplyDto> listOfBoard(
            @Param("bno") Long bno,
            @Param("skip") int skip,
            @Param("limit") int limit
    );

    int countOfBoard(@Param("bno") Long bno);

}
