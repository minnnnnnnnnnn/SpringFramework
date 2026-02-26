package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ReplyDto;
import org.zerock.dto.ReplyListPagingDto;
import org.zerock.mapper.ReplyMapper;
import org.zerock.service.exception.ReplyException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ReplyService {

    private final ReplyMapper replyMapper;

    public void add(ReplyDto replyDto) {

        try {
            replyMapper.insert(replyDto);
        } catch (Exception e) {
            throw new ReplyException(500, "Insert Error");
        }
    }

    public ReplyDto getOne(Long rno) {

        try {
            return replyMapper.read(rno);
        } catch (Exception e) {
            throw new ReplyException(404, "Not Found");
        }
    }

    public void modify(ReplyDto replyDto) {

        try {
            int count = replyMapper.update(replyDto);

            if (count == 0) {
                throw new ReplyException(404, "Not Found");
            }
        } catch (Exception e) {
            throw new ReplyException(500, "Update Error");
        }
    }

    public void remove(Long rno) {

        try {
            int count = replyMapper.delete(rno);

            if (count == 0) {
                throw new ReplyException(404, "Not Found");
            }
        } catch (Exception e) {
            throw new ReplyException(500, "Delete Error");
        }
    }

    public ReplyListPagingDto listOfBoard(Long bno, int page, int size) {

        try {
            int skip = (page - 1) * size;

            List<ReplyDto> replyDtoList = replyMapper.listOfBoard(bno, skip, size);

            int count = replyMapper.countOfBoard(bno);

            return new ReplyListPagingDto(replyDtoList, count, page, size);

        } catch (Exception e) {
            throw new ReplyException(500, e.getMessage());
        }
    }

}
