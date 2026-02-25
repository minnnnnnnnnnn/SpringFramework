package org.zerock.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.BoardDto;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testInsert() {
        BoardDto boardDto = BoardDto.builder()
                .title("title")
                .content("content")
                .writer("user00")
                .build();

        int insertCount = boardMapper.insert(boardDto);

        log.info("--------");
        log.info("insertCount: "+ insertCount);

        log.info("--------");
        log.info("BNO: "+ boardDto.getBno());
    }

    @Test
    public void testSelctOne() {
        Long bno = 2L;

        BoardDto board = boardMapper.selectOne(bno);

        log.info("board: " + board);
    }

    @Test
    public void testRemove() {
        Long bno = 2L;

        int removeCount = boardMapper.remove(bno);

        log.info("--------");
        log.info("removeCount: " + removeCount);
    }

    @Test
    public void testUpdate() {
        BoardDto board = BoardDto.builder()
                .bno(2L)
                .title("update Title")
                .content("update content")
                .delFlag(false)
                .build();

        int updateCount = boardMapper.update(board);

        log.info("--------");
        log.info("updateCount: " + updateCount);
    }

    @Test
    public void testList() {
        List<BoardDto> dtoList = boardMapper.list();

        log.info("--------");
        log.info(dtoList);

        dtoList.stream().forEach(log::info);
    }

    @Test
    public void testList2() {

        int page = 2;

        int skip = (page - 1) * 10;
        int count = 10;

        List<BoardDto> dtoList = boardMapper.list2(skip, count);

        dtoList.stream().forEach(log::info);
    }

    @Test
    public void testSearch() {

        int page = 2;

        int skip = (page -1) * 10;
        int count = 10;

        String[] types = new String[] {"T", "C", "W"};
        String keyword = "Test";

        boardMapper.listSearch(skip, count, types, keyword);
    }
}
