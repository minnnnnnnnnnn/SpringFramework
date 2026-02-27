package org.zerock.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.ReplyDto;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ReplyMapperTests {

    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void testInsert() {

        Long bno = 1L;

        ReplyDto replyDto = ReplyDto.builder()
                .bno(bno)
                .replyText("Reply...")
                .replyer("user1")
                .build();

        replyMapper.insert(replyDto);
    }

    @Test void testInserts() {

        Long[] bnos = {6131L, 6130L, 6129L};

        for (Long bno : bnos) {
            for (int i = 0; i< 10; i ++) {
                ReplyDto replyDto = ReplyDto.builder()
                        .bno(bno)
                        .replyer("replyer1")
                        .replyText("Sample Reply")
                        .build();

                replyMapper.insert(replyDto);
            }
        }
    }

    @Test
    public void testRead() {

        Long rno = 2L;

        log.info("--------");
        log.info(replyMapper.read(rno));
    }

    @Test
    public void testDelete() {

        Long rno = 35L;

        log.info("--------");
        log.info(replyMapper.delete(rno));
    }

    @Test
    public void testUpdate() {

        ReplyDto replyDto = ReplyDto.builder()
                .rno(2L)
                .replyText("Update ReplyText")
                .build();

        replyMapper.update(replyDto);
    }

    @Test
    public void testListOfBoard() {

        Long bno = 6131L;

        List<ReplyDto> replyList = replyMapper.listOfBoard(bno, 0, 10);

        replyList.forEach(log::info);
    }
}
