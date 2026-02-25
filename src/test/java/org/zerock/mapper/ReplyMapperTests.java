package org.zerock.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.ReplyDto;

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
}
